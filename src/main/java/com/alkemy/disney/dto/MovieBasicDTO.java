package com.alkemy.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MovieBasicDTO {
    private String image;
    private String title;
    private Date releaseDate;
}
