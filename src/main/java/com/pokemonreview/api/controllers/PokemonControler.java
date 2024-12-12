package com.pokemonreview.api.controllers;

import com.pokemonreview.api.models.pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PokemonControler {
    @GetMapping("pokemon")
    public ResponseEntity<List<pokemon>> getPokemons(){
        pokemon pikachu = new pokemon(1, "Pikachu", "Electric");
        return ResponseEntity.ok(List.of(pikachu));
    }
    @GetMapping("pokemon/{id}")
    public pokemon getPokemonById(@PathVariable int id)
    {
        return new pokemon(id, "Pikachu", "Electric");
    }
    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<pokemon> createPokemon(@RequestBody pokemon pokemon)
    {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());

        return new ResponseEntity<>(pokemon, HttpStatus.CREATED);
    }
    @PutMapping("pokemon/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<pokemon> updatePokemon(@PathVariable int id, @RequestBody pokemon pokemon)
    {
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());

        return new ResponseEntity<>(pokemon, HttpStatus.OK);
    }
    @DeleteMapping("pokemon/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePokemon(@PathVariable int id)
    {
        return new ResponseEntity<>("Pokemon deleted successfully", HttpStatus.OK);
    }
}
