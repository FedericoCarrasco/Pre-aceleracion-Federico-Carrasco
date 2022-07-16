package com.alkemy.disney.service;

import com.alkemy.disney.dto.MovieDTO;

public interface MovieService {

    public MovieDTO save(MovieDTO movie);

    public void delete(long id);

}
