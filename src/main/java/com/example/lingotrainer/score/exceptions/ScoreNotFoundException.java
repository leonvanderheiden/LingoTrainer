package com.example.lingotrainer.score.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ScoreNotFoundException extends RuntimeException {
    public ScoreNotFoundException(@PathVariable Long scoreId){
        super("Score not found with id: " + scoreId);
    }
}