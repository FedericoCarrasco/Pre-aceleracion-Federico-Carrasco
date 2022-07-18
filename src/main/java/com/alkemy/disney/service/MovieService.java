package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO movie);

    MovieDTO update(MovieDTO newMovie, Long id);

    MovieDTO getById(Long id);

    List<MovieDTO> getByFilters(String name, Long genre, String order);

    void delete(long id);

    MovieDTO addCharacter(Long idMovie, Long idCharacter);

    MovieDTO removeCharacter(Long idMovie, Long idCharacter);

}
