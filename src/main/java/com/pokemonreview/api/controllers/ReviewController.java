package com.pokemonreview.api.controllers;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ReviewController {
    private ReviewService _reviewService;
    public ReviewController(ReviewService reviewService)
    {
        this._reviewService = reviewService;
    }
    @GetMapping("pokemon/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews()
    {
        List<ReviewDto> reviews = this._reviewService.getReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("pokemon/review/create")
    public ResponseEntity<ReviewDto> createReview(@RequestBody  ReviewDto review)
    {
        ReviewDto reviewDto = this._reviewService.createReview(review);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }
    @GetMapping("pokemon/{pokemonId}/review")
    public ResponseEntity<List<ReviewDto>> getReviewByPokemonId(@PathVariable int pokemonId)
    {
        List<ReviewDto> reviews = this._reviewService.getReviewByPokemonId(pokemonId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("pokemon/review/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable int id)
    {
        ReviewDto review = this._reviewService.findReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
    @GetMapping("pokemon/{pokemonId}/review/{reviewId}/update")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int pokemonId, @PathVariable int reviewId, @RequestBody ReviewDto review)
    {
        ReviewDto reviewDto = this._reviewService.updateReview(pokemonId, reviewId, review);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }
    @GetMapping("pokemon/{pokemonId}/review/{reviewId}/delete")
    public ResponseEntity<String> deleteReview(@PathVariable int pokemonId, @PathVariable int reviewId)
    {
        this._reviewService.deleteReview(reviewId, pokemonId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
