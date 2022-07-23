package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO genre);

    GenreDTO getById(Long id);

    List<GenreDTO> getAllGenres();

}
