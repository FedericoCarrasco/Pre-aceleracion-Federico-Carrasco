package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieBasicDTO;
import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO movie);

    MovieDTO getById(Long id);

    List<MovieBasicDTO> getByFilters(String name, Long genre, String order);

    MovieDTO update(MovieDTO newMovie, Long id);

    void delete(long id);

    MovieDTO addCharacter(Long idMovie, Long idCharacter);

    MovieDTO removeCharacter(Long idMovie, Long idCharacter);

}
