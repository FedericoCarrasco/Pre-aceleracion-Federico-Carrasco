package com.alkemy.disney.service;

import com.alkemy.disney.dto.CharacterDTO;

public interface CharacterService {

    public CharacterDTO save(CharacterDTO character);

    public void delete(long id);

}
