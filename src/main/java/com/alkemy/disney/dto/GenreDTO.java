package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GenreDTO {
    private long id;
    @NotNull
    @Size(min = 1)
    private String name;
    @NotNull
    private String image;
}
