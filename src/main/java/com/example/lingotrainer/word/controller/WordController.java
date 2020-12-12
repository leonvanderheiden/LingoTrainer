package com.example.lingotrainer.word.controller;

import com.example.lingotrainer.word.domain.Word;
import com.example.lingotrainer.word.service.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @Autowired
    public WordServiceInterace wordsService;

    //Returned een random woord van een bepaalde lengte in de database
    @GetMapping("/getrandomword/{length}")
    public Word getRandomWord(@PathVariable Long length) {
        return wordsService.getRandomWordByLength(length);
    }
}
