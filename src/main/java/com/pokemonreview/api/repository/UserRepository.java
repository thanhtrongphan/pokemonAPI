package com.pokemonreview.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pokemonreview.api.models.userEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<userEntity, Integer> {
    Optional<userEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
