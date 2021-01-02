package com.example.lingotrainer.highscore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class HighscoreNotFoundException extends RuntimeException {
    public HighscoreNotFoundException(@PathVariable Long highscoreId){
        super("Highscore not found with id: " + highscoreId);
    }
}
