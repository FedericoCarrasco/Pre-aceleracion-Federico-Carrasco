package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO character);

    CharacterDTO getById(Long id);

    List<CharacterBasicDTO> getByFilters(String name, Integer age, Double weight, Set<Long> movies);

    CharacterDTO update(CharacterDTO newCharacter, Long id);

    void delete(long id);

}
