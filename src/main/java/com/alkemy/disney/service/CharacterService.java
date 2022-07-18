package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO character);

    CharacterDTO update(CharacterDTO newCharacter, Long id);

    CharacterDTO getById(Long id);

    List<CharacterDTO> getByFilters(String name, Integer age, Double weight, Set<Long> movies);

    void delete(long id);

}
