package com.pokemonreview.api.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String name;
    private String content;
    private int stars;
    private int pokemonId;
}
