package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<pokemon, Integer> {

}
