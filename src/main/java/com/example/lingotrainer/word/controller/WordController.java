package com.example.lingotrainer.word.controller;

import com.example.lingotrainer.word.service.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class WordController {

    @Autowired
    public WordServiceInterace wordsService;

    @GetMapping("/words/{totalLetters}")
    public List<String> getWords(@PathVariable Long totalLetters) throws FileNotFoundException {
        return wordsService.getWords(totalLetters);
    }
}
