package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {

    public GenreDTO save(GenreDTO genre);

    public List<GenreDTO> getAllGenres();

}
