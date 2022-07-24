package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exeption.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecifications;
import com.alkemy.disney.service.GenreService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieSpecifications movieSpecifications;

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        entitySaved.setGenre(genreService.getGenreEntityById(entitySaved.getGenreId()));
        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    public MovieDTO getById(Long id) {
        MovieEntity entity = getMovieEntityById(id);
        return movieMapper.movieEntity2DTO(entity, true);
    }

    public List<MovieBasicDTO> getByFilters(String name, Long genre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecifications.getByFilters(filtersDTO));
        return movieMapper.movieEntityCollection2BasicDTOList(entities);
    }

    public MovieDTO update(MovieDTO newMovie, Long id) {
        MovieEntity movie = getMovieEntityById(id);
        movie.setTitle(newMovie.getTitle());
        movie.setImage(newMovie.getImage());
        movie.setReleaseDate(newMovie.getReleaseDate());
        movie.setRating(newMovie.getRating());
        movie.setGenreId(newMovie.getGenreId());
        movie.setGenre(genreService.getGenreEntityById(movie.getGenreId()));
        movieRepository.save(movie);
        return movieMapper.movieEntity2DTO(movie, false);
    }

    public void delete(long id) {
        MovieEntity movie = getMovieEntityById(id);
        movieRepository.delete(movie);
    }

    public MovieDTO addCharacter(Long idMovie, Long idCharacter) {
        MovieEntity movie = getMovieEntityById(idMovie);
        CharacterEntity character = getCharacterEntityById(idCharacter);
        movie.addCharacter(character);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    public MovieDTO removeCharacter(Long idMovie, Long idCharacter) {
        MovieEntity movie = getMovieEntityById(idMovie);
        CharacterEntity character = getCharacterEntityById(idCharacter);
        movie.removeCharacter(character);
        return movieMapper.movieEntity2DTO(movie, true);
    }

    private MovieEntity getMovieEntityById(Long id) {
        Optional<MovieEntity> movie = movieRepository.findById(id);
        if(movie.isEmpty()){
            throw new ParamNotFound("Movie with id: " + id + " not found");
        }
        return movie.get();
    }

    private CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if(character.isEmpty()){
            throw new ParamNotFound("Character with id: " + id + " not found");
        }
        return character.get();
    }

}
