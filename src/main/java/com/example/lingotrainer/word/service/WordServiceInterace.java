package com.example.lingotrainer.word.service;

import com.example.lingotrainer.word.domain.Word;

public interface WordServiceInterace {

    Word save(Word word);

    Word getRandomWordByLength(Long length);
}

