package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.pokemon;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class PokemonControler {
    private PokemonService _pokemonService;
    public PokemonControler(PokemonService pokemonService)
    {
        this._pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        PokemonResponse pokemons = this._pokemonService.getPokemons(pageNo, pageSize);
        return new ResponseEntity<>(pokemons, HttpStatus.OK);
    }

    @GetMapping("pokemon/{id}")
    public PokemonDto getPokemonById(@PathVariable int id)
    {
        PokemonDto pokemon = this._pokemonService.getPokemonById(id);
        return pokemon;
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemon)
    {
        PokemonDto pokemonDto = this._pokemonService.createPokemon(pokemon);
        return new ResponseEntity<>(pokemonDto, HttpStatus.CREATED);

    }


    @PutMapping("pokemon/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PokemonDto> updatePokemon(@PathVariable int id, @RequestBody PokemonDto pokemonResquest)
    {
        PokemonDto pokemonUpdate = this._pokemonService.updatePokemon(id, pokemonResquest);
        return new ResponseEntity<>(pokemonUpdate, HttpStatus.OK);
    }


    @DeleteMapping("pokemon/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deletePokemon(@PathVariable int id)
    {
        this._pokemonService.deletePokemon(id);
        return new ResponseEntity<>("Pokemon deleted", HttpStatus.OK);
    }
}
