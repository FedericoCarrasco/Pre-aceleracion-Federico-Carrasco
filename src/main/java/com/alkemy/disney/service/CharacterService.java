package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO character);

    List<CharacterDTO> getByFilters(String name, Integer age, Double weight, Set<Long> movies);

    void delete(long id);

}
