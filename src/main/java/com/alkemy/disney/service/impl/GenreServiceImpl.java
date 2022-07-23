package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.GenreDTO;
import com.alkemy.disney.entity.GenreEntity;
import com.alkemy.disney.exeption.ParamNotFound;
import com.alkemy.disney.mapper.GenreMapper;
import com.alkemy.disney.repository.GenreRepository;
import com.alkemy.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        return genreMapper.genreEntity2DTO(entitySaved);
    }

    public GenreDTO getById(Long id) {
        GenreEntity genre = getGenreEntityById(id);
        return genreMapper.genreEntity2DTO(genre);
    }

    public List<GenreDTO> getAllGenres(){
        List<GenreEntity> entities = genreRepository.findAll();
        return genreMapper.genreEntityList2DTOList(entities);
    }

    private GenreEntity getGenreEntityById(Long id) {
        Optional<GenreEntity> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw new ParamNotFound("Genre with id: " + id + " not found");
        }
        return genre.get();
    }

}
