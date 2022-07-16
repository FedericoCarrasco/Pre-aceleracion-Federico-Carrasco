package com.alkemy.disney.mapper;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterEntity characterDTO2Entity (CharacterDTO dto) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(dto.getName());
        characterEntity.setImage(dto.getImage());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setStory(dto.getStory());
        characterEntity.setMovies(dto.getMovies());
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO (CharacterEntity entity) {
        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setName(entity.getName());
        characterDTO.setImage(entity.getImage());
        characterDTO.setAge(entity.getAge());
        characterDTO.setWeight(entity.getWeight());
        characterDTO.setStory(entity.getStory());
        characterDTO.setMovies(entity.getMovies());
        return characterDTO;
    }

    public List<CharacterDTO> characterEntityList2DTOList (List<CharacterEntity> entities) {
        List<CharacterDTO> DTOs = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            DTOs.add(characterEntity2DTO(entity));
        }
        return DTOs;
    }
}
