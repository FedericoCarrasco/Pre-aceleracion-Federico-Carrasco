package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.exeption.ParamNotFound;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.specification.CharacterSpecifications;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterSpecifications characterSpecifications;

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    public CharacterDTO getById(Long id) {
        CharacterEntity entity = getCharacterEntityById(id);
        return characterMapper.characterEntity2DTO(entity, true);
    }

    public List<CharacterBasicDTO> getByFilters(String name, Integer age, Double weight, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecifications.getByFilters(filtersDTO));
        return characterMapper.characterEntityCollection2BasicDTOList(entities);
    }

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        return characterMapper.characterEntity2DTO(entitySaved, true);
    }

    public CharacterDTO update(CharacterDTO newCharacter, Long id) {
        CharacterEntity character = getCharacterEntityById(id);
        character.setName(newCharacter.getName());
        character.setImage(newCharacter.getImage());
        character.setAge(newCharacter.getAge());
        character.setWeight(newCharacter.getWeight());
        character.setStory(newCharacter.getStory());
        return characterMapper.characterEntity2DTO(character, false);
    }

    public void delete(long id) {
        CharacterEntity character = getCharacterEntityById(id);
        characterRepository.delete(character);
    }

    private CharacterEntity getCharacterEntityById(Long id) {
        Optional<CharacterEntity> character = characterRepository.findById(id);
        if (character.isEmpty()) {
            throw new ParamNotFound("Character with Id: " + id + " not found.");
        }
        return character.get();
    }
}
