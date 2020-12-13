package com.example.lingotrainer.word.service;

import com.example.lingotrainer.word.domain.Word;

public interface WordServiceInterace {

    Word findById(long id);

    Word getRandomWordByLength(Long length);

    Word updateById(Long wordid, Word word);

    Boolean deleteById(Long wordid);

    Word save(Word word);
}

