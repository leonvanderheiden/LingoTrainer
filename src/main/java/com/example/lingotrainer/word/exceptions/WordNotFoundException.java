package com.example.lingotrainer.word.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(@PathVariable Long wordId){
        super("Word not found with id: " + wordId);
    }
}
