package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor") // Por alguna razón si nombro a esta tabla "character" hibernate no la crea en mysql.
@Getter
@Setter
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String image;

    private int age;

    private double weight;

    private String story;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private Set<MovieEntity> movies = new HashSet<>();

}
