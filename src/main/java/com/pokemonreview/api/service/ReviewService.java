package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviews();
    List<ReviewDto> getReviewByPokemonId(int pokemonId);
    ReviewDto createReview(ReviewDto reviewDto);

}