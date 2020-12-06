package com.example.lingotrainer.word.controller;

import com.example.lingotrainer.filterwords.application.FilterWordsProcessor;
import com.example.lingotrainer.filterwords.data.FileWordReader;
import com.example.lingotrainer.filterwords.domain.FileWordWriter;
import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import com.example.lingotrainer.score.Score;
import com.example.lingotrainer.word.Word;
import com.example.lingotrainer.word.service.WordServiceInterace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class WordController {

    @Autowired
    public WordServiceInterace wordsService;

    @GetMapping("/savewords")
    public Boolean saveWords() throws FileNotFoundException {
        FilterWordsProcessor fwp = new FilterWordsProcessor(new FileWordReader(), new LingoWordFilter(), new FileWordWriter());
        for (String words : fwp.processWordsSnel()) {
            Word word = new Word(words);
            //wordsService.save(word);
        }
        return true;
    }
}
