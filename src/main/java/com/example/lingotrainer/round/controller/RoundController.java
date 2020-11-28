package com.example.lingotrainer.round.controller;

import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.round.service.RoundServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class RoundController {

    @Autowired
    public RoundServiceInterface roundService;

    @GetMapping("/round/{id}")
    public Round getWords(@PathVariable Long id) {
        return roundService.getRound(id);
    }
}
