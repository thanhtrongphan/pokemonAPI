package com.pokemonreview.api.controllers;

import com.pokemonreview.api.models.pokemon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PokemonControler {
    @GetMapping("/pokemon")
    public ResponseEntity<List<pokemon>> getPokemons(){
        pokemon pikachu = new pokemon(1, "Pikachu", "Electric");
        return ResponseEntity.ok(List.of(pikachu));
    }
}
