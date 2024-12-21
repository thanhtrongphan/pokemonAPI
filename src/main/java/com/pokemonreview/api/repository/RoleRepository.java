package com.pokemonreview.api.repository;
import com.pokemonreview.api.models.role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<role, Integer> {
    Optional<role> findByName(String name);
}
