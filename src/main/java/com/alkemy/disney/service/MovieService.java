package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO movie);

    List<MovieDTO> getByFilters(String name, Long genre, String order);

    void delete(long id);

}
