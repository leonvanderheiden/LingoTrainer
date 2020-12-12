package com.example.lingotrainer.filterwords.domain;

import com.example.lingotrainer.score.data.ScoreRepository;
import com.example.lingotrainer.word.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileWordWriter implements WordWriter {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    public FileWordWriter() {

    }

    @Override
    public boolean writeWords(List<String> validWords) {
        for (String word : validWords) {

            Word newWord = new Word(word);

        }
        return true;
    }
}
