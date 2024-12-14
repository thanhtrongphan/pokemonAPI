package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.models.pokemon;

import java.util.List;

public interface PokemonService {
    PokemonResponse getPokemons(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonDto updatePokemon(int id, PokemonDto pokemonDto);
    void deletePokemon(int id);

}
