package com.pokemonreview.api.service.impl;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.models.pokemon;
import com.pokemonreview.api.models.review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository _reviewRepository;
    private PokemonRepository _pokemonRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this._reviewRepository = reviewRepository;
        this._pokemonRepository = pokemonRepository;
    }

    @Override
    public List<ReviewDto> getReviews() {
        List<review> reviews = this._reviewRepository.findAll();
        List<ReviewDto> reviewDtos = reviews.stream().map(this::mapToDto).toList();
        return reviewDtos;
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(int pokemonId) {
        List<review> reviews = this._reviewRepository.findByPokemon_Id(pokemonId);
        List<ReviewDto> reviewDtos = reviews.stream().map(this::mapToDto).toList();
        return reviewDtos;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        review review = mapToEntity(reviewDto);
        this._reviewRepository.save(review);
        ReviewDto reviewResponse = mapToDto(review);
        return reviewResponse;

    }

    @Override
    public ReviewDto findReviewById(int id) {
        review review = this._reviewRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Review not found"));
        ReviewDto reviewDto = mapToDto(review);
        return reviewDto;
    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        pokemon pokemon = this._pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        review review = this._reviewRepository.findById(reviewId).orElseThrow(() -> new PokemonNotFoundException("Review not found"));
        if (review.getPokemon().getId() != pokemon.getId())
        {
            throw new PokemonNotFoundException("Review not found");
        }
        review.setName(reviewDto.getName());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        review.setPokemon(pokemon);
        this._reviewRepository.save(review);
        ReviewDto reviewResponse = mapToDto(review);
        return reviewResponse;
    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {
        pokemon pokemon = this._pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        review review = this._reviewRepository.findById(reviewId).orElseThrow(() -> new PokemonNotFoundException("Review not found"));
        if (review.getPokemon().getId() != pokemon.getId())
        {
            throw new PokemonNotFoundException("Review not found");
        }
        this._reviewRepository.delete(review);
    }

    public ReviewDto mapToDto(review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setName(review.getName());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        reviewDto.setPokemonId(review.getPokemon().getId());
        return reviewDto;
    }
    public review mapToEntity(ReviewDto reviewDto) {
        review review = new review();
        review.setId(reviewDto.getId());
        review.setName(reviewDto.getName());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        int pokemonId = reviewDto.getPokemonId();
        pokemon pokemon = this._pokemonRepository.findById(pokemonId).orElseThrow(() -> new PokemonNotFoundException("Pokemon not found"));
        review.setPokemon(pokemon);
        return review;
    }
}
