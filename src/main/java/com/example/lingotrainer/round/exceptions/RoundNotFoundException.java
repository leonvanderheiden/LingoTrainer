package com.example.lingotrainer.round.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class RoundNotFoundException extends RuntimeException {
    public RoundNotFoundException(@PathVariable Long roundId){
        super("Round not found with id: " + roundId);
    }
}
