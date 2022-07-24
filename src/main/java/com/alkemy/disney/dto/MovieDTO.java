package com.alkemy.disney.dto;

import com.alkemy.disney.entity.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private long id;
    @NotNull
    @Size(min = 1)
    private String title;
    private String image;
    private Date releaseDate;
    private float rating;
    private GenreEntity genre;
    private long genreId;
    private Set<CharacterDTO> characters = new HashSet<>();
}
