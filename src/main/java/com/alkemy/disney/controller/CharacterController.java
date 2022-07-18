package com.alkemy.disney.controller;

import com.alkemy.disney.dto.CharacterDTO;
import com.alkemy.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService service;

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable Long id) {
        CharacterDTO character = service.getById(id);
        return ResponseEntity.ok(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Double weight,
            @RequestParam(required = false) Set<Long> movies
    ) {
        List<CharacterDTO> characters = service.getByFilters(name, age, weight, movies);
        return ResponseEntity.ok(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO savedCharacter = service.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO newCharacter, @PathVariable Long id) {
        CharacterDTO savedCharacter = service.update(newCharacter, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
