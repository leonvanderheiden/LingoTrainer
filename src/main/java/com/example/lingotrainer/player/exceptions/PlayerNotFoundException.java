package com.example.lingotrainer.player.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(@PathVariable Long playerId){
        super("Player not found with id: " + playerId);
    }
}
