package com.example.lingotrainer.word.service;

import com.example.lingotrainer.word.Word;
import com.example.lingotrainer.word.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class WordService implements WordServiceInterace {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    public WordService() {

    }

    public Word save(Word word) {
        return wordRepository.save(word);
    }
}
