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
    @GetMapping("review")
    public ResponseEntity<List<ReviewDto>> getReviews()
    {
        List<ReviewDto> reviews = this._reviewService.getReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("review/create")
    public ResponseEntity<ReviewDto> createReview(@RequestBody  ReviewDto review)
    {
        ReviewDto reviewDto = this._reviewService.createReview(review);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }
    @GetMapping("review/{pokemonId}")
    public ResponseEntity<List<ReviewDto>> getReviewByPokemonId(@PathVariable int pokemonId)
    {
        List<ReviewDto> reviews = this._reviewService.getReviewByPokemonId(pokemonId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
