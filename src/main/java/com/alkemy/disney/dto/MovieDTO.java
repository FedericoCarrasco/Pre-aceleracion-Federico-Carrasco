package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GenreEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties("hibernateLazyInitializer")
public class MovieDTO {
    private long id;
    private String title;
    private String image;
    private Date releaseDate;
    private float rating;
    private GenreEntity genre;
    private long genreId;
    private Set<CharacterDTO> characters = new HashSet<>();
}
