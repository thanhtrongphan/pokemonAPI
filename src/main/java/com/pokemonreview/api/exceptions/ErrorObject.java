package com.pokemonreview.api.exceptions;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
