package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.nio.channels.ScatteringByteChannel;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {
    private PokemonRepository _pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this._pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonResponse getPokemons(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<pokemon> pokemons = this._pokemonRepository.findAll(pageable);
        List<pokemon> pokemonList = pokemons.getContent();
        List<PokemonDto> content = pokemonList.stream().map(this::mapToDto).collect(Collectors.toList());

        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setContent(content);
        pokemonResponse.setPageNo(pokemons.getNumber());
        pokemonResponse.setPageSize(pokemons.getSize());
        pokemonResponse.setTotalElements(pokemons.getTotalElements());
        pokemonResponse.setTotalPages(pokemons.getTotalPages());
        pokemonResponse.setLast(pokemons.isLast());

        return pokemonResponse;
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        pokemon pokemonResponse = this._pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        PokemonDto pokemonDto = mapToDto(pokemonResponse);
        return pokemonDto;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        pokemon pokemon = mapToEntity(pokemonDto);
        this._pokemonRepository.save(pokemon);

        PokemonDto PokemonResponse = mapToDto(pokemon);
        return PokemonResponse;
    }

    @Override
    public PokemonDto updatePokemon(int id, PokemonDto pokemonDto) {
        pokemon pokemonDta = this._pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        pokemonDta.setName(pokemonDto.getName());
        pokemonDta.setType(pokemonDto.getType());
        pokemon updatePokemon =  this._pokemonRepository.save(pokemonDta);
        return mapToDto(updatePokemon);
    }

    @Override
    public void deletePokemon(int id) {

        pokemon pokemon = this._pokemonRepository.findById(id).orElseThrow(()-> new PokemonNotFoundException("Pokemon not found"));
        this._pokemonRepository.delete(pokemon);
    }
    public PokemonDto mapToDto(pokemon pokemon)
    {
        PokemonDto pokemonDto = new PokemonDto();
        pokemonDto.setId(pokemon.getId());
        pokemonDto.setName(pokemon.getName());
        pokemonDto.setType(pokemon.getType());
        return pokemonDto;
    }
    public pokemon mapToEntity(PokemonDto pokemonDto)
    {
        pokemon pokemon = new pokemon();
        pokemon.setId(pokemonDto.getId());
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());
        return pokemon;
    }
}
