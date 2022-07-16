package com.alkemy.disney.dto;

import com.alkemy.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private long id;
    private String name;
    private String image;
    private int age;
    private double weight;
    private String story;
    private Set<MovieEntity> movies = new HashSet<>();
}
