package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.repository.specification.MovieSpecifications;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieSpecifications movieSpecifications;
    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> getByFilters(String name, Long genre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecifications.getByFilters(filtersDTO));
        return movieMapper.movieEntityCollection2DTOList(entities, true);
    }

    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    public void delete(long id) {
        movieRepository.deleteById(id);
    }

}
