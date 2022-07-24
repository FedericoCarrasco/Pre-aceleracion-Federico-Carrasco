package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity (CharacterDTO dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        for (MovieDTO movieDTO : dto.getMovies()) {
            MovieEntity movie = movieMapper.movieDTO2Entity(movieDTO);
            entity.getMovies().add(movie);
        }
        return entity;
    }

    public CharacterDTO characterEntity2DTO (CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if (loadMovies) {
            dto.setMovies(movieMapper.movieEntityCollection2DTOSet(entity.getMovies(), false));
        }
        return dto;
    }

    public CharacterBasicDTO characterEntity2BasicDTO (CharacterEntity entity) {
        CharacterBasicDTO dto = new CharacterBasicDTO();
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }

    public Set<CharacterDTO> characterEntityCollection2DTOSet(Collection<CharacterEntity> entities, boolean loadMovies) {
        Set<CharacterDTO> DTOs = new HashSet<>();
        for (CharacterEntity entity : entities) {
            DTOs.add(characterEntity2DTO(entity, loadMovies));
        }
        return DTOs;
    }

    public List<CharacterDTO> characterEntityCollection2DTOList (Collection<CharacterEntity> entities, boolean loadMovies) {
        List<CharacterDTO> DTOs = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            DTOs.add(characterEntity2DTO(entity, loadMovies));
        }
        return DTOs;
    }

    public List<CharacterBasicDTO> characterEntityCollection2BasicDTOList (Collection<CharacterEntity> entities) {
        List<CharacterBasicDTO> DTOs = new ArrayList<>();
        for(CharacterEntity entity : entities) {
            DTOs.add(characterEntity2BasicDTO(entity));
        }
        return DTOs;
    }

}
