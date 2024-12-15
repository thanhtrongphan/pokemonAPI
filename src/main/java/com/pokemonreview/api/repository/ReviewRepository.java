package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<review, Integer> {
    public List<review> findByPokemon_Id(int pokemonId);
}
