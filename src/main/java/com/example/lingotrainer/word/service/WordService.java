package com.example.lingotrainer.word.service;

import com.example.lingotrainer.word.Word;
import com.example.lingotrainer.word.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WordService implements WordServiceInterace {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    public WordService() {

    }

    @Override
    public Word getRandomWordByLength(Long length) {
        List<Word> words = new ArrayList<>();
        for (Word w : wordRepository.getAllByIdNotNull()) {
            if (w.getWord().length() == length) {
                words.add(w);
            }
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public Word save(Word word) {
        return wordRepository.save(word);
    }
}
