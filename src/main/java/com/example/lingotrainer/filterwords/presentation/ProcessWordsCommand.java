package com.example.lingotrainer.filterwords.presentation;

import com.example.lingotrainer.filterwords.application.FilterWordsProcessor;
import com.example.lingotrainer.filterwords.domain.WordReader;
import com.example.lingotrainer.filterwords.domain.WordWriter;
import com.example.lingotrainer.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
public class ProcessWordsCommand {
    @Autowired
    public FilterWordsProcessor filterWordsProcessor;


    public ProcessWordsCommand() {

    }

    public ProcessWordsCommand(FilterWordsProcessor filterWordsProcessor) {
        this.filterWordsProcessor = filterWordsProcessor;
    }

    @GetMapping("/test")
    public Boolean getWords() throws FileNotFoundException {
        filterWordsProcessor.processWords();
        return true;
    }
}
