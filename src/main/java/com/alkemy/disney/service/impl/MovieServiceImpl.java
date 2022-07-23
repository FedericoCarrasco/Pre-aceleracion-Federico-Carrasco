package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exeption.ParamNotFound;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecifications;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieSpecifications movieSpecifications;
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public MovieDTO getById(Long id) {
        Optional<MovieEntity> entity = movieRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ParamNotFound("Movie with Id: " + id + " not found.");
        }
        return movieMapper.movieEntity2DTO(entity.get(), true);
    }

    public List<MovieDTO> getByFilters(String name, Long genre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecifications.getByFilters(filtersDTO));
        return movieMapper.movieEntityCollection2DTOList(entities, true);
    }

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    public MovieDTO update(MovieDTO newMovie, Long id) {
        Optional<MovieEntity> oldMovie = movieRepository.findById(id);
        if(oldMovie.isEmpty()){
            throw new ParamNotFound("Movie with id: " + id + " not found");
        }
        oldMovie.get().setTitle(newMovie.getTitle());
        oldMovie.get().setImage(newMovie.getImage());
        oldMovie.get().setReleaseDate(newMovie.getReleaseDate());
        oldMovie.get().setRating(newMovie.getRating());
        oldMovie.get().setGenreId(newMovie.getGenreId());
        oldMovie.get().setGenre(newMovie.getGenre());
        MovieEntity entitySaved = movieRepository.save(oldMovie.get());
        return movieMapper.movieEntity2DTO(entitySaved, false);
    }

    public void delete(long id) {
        movieRepository.deleteById(id);
    }

    public MovieDTO addCharacter(Long idMovie, Long idCharacter) {
        Optional<MovieEntity> movie = movieRepository.findById(idMovie);
        if(movie.isEmpty()){
            throw new ParamNotFound("Movie with id: " + idMovie + " not found");
        }
        Optional<CharacterEntity> character = characterRepository.findById(idCharacter);
        if(character.isEmpty()){
            throw new ParamNotFound("Character with id: " + idCharacter + " not found");
        }
        return movieMapper.addCharacter(movie.get(), character.get());
    }

    public MovieDTO removeCharacter(Long idMovie, Long idCharacter) {
        Optional<MovieEntity> movie = movieRepository.findById(idMovie);
        if(movie.isEmpty()){
            throw new ParamNotFound("Movie with id: " + idMovie + " not found");
        }
        Optional<CharacterEntity> character = characterRepository.findById(idCharacter);
        if(character.isEmpty()){
            throw new ParamNotFound("Character with id: " + idCharacter + " not found");
        }
        return movieMapper.removeCharacter(movie.get(), character.get());
    }
}
