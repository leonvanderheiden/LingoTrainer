package com.example.lingotrainer.word.application;

import com.example.lingotrainer.word.data.WordRepository;
import com.example.lingotrainer.word.domain.Word;
import com.example.lingotrainer.word.exceptions.WordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class WordService implements WordServiceInterace {

    @Autowired
    WordRepository wordRepository;

    @Autowired
    public WordService() { }

    @Override
    public Word findById(long wordid) {
        return wordRepository.findById(wordid).orElseThrow(() -> new WordNotFoundException(wordid));
    }

    @Override
    public Word updateById(Long wordid, Word word) {
        Word w = wordRepository.findById(wordid).orElseThrow(() -> new WordNotFoundException(wordid));

        w.setWord(word.getWord());

        return wordRepository.save(w);
    }

    @Override
    public Boolean deleteById(Long wordid) {
        Word w = wordRepository.findById(wordid).orElseThrow(() -> new WordNotFoundException(wordid));
        if (w != null) {
            wordRepository.deleteById(w.getId());
        }
        return (w != null);
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
