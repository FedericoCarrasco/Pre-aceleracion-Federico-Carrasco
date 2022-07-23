package com.alkemy.disney.service.impl;

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
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ParamNotFound("Character with Id: " + id + " not found.");
        }
        return characterMapper.characterEntity2DTO(entity.get(), true);
    }

    public List<CharacterDTO> getByFilters(String name, Integer age, Double weight, Set<Long> movies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, movies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecifications.getByFilters(filtersDTO));
        return characterMapper.characterEntityCollection2DTOList(entities, true);
    }

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        return characterMapper.characterEntity2DTO(entitySaved, true);
    }

    public CharacterDTO update(CharacterDTO newCharacter, Long id) {
        Optional<CharacterEntity> oldCharacter = characterRepository.findById(id);
        if (oldCharacter.isEmpty()) {
            throw new ParamNotFound("Character with Id: " + id + " not found.");
        }
        oldCharacter.get().setName(newCharacter.getName());
        oldCharacter.get().setImage(newCharacter.getImage());
        oldCharacter.get().setAge(newCharacter.getAge());
        oldCharacter.get().setWeight(newCharacter.getWeight());
        oldCharacter.get().setStory(newCharacter.getStory());
        CharacterEntity entitySaved = characterRepository.save(oldCharacter.get());
        return characterMapper.characterEntity2DTO(entitySaved, false);
    }

    public void delete(long id) {
        characterRepository.deleteById(id);
    }

}
