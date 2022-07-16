package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    CharacterMapper characterMapper;

    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setReleaseDate(dto.getReleaseDate());
        entity.setRating(dto.getRating());
        entity.setGenre(dto.getGenre());
        entity.setGenreId(dto.getGenreId());
        for (CharacterDTO characterDTO : dto.getCharacters()) {
            CharacterEntity theCharacter = characterMapper.characterDTO2Entity(characterDTO);
            entity.getCharacters().add(theCharacter);
        }
        return entity;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setReleaseDate(entity.getReleaseDate());
        dto.setRating(entity.getRating());
        dto.setGenre(entity.getGenre());
        dto.setGenreId(entity.getGenreId());
        for (CharacterEntity characterEntity : entity.getCharacters()) {
            dto.getCharacters().add(characterMapper.characterEntity2DTO(characterEntity));
        }
        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities){
        List<MovieDTO> DTOs = new ArrayList<>();
        for (MovieEntity entity : entities){
            DTOs.add(movieEntity2DTO(entity));
        }
        return DTOs;
    }
}
