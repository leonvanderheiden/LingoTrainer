package com.example.lingotrainer.filterwords.domain;

import com.example.lingotrainer.filterwords.domain.WordWriter;
import com.example.lingotrainer.score.repository.ScoreRepository;
import com.example.lingotrainer.word.Word;
import com.example.lingotrainer.word.repository.WordRepository;
import com.example.lingotrainer.word.service.WordServiceInterace;
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
