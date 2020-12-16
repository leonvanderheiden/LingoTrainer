package com.example.lingotrainer.filterwords.application;

import com.example.lingotrainer.filterwords.domain.WordFilter;
import com.example.lingotrainer.filterwords.domain.WordReader;
import com.example.lingotrainer.filterwords.domain.WordWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FilterWordsProcessor {

    @Autowired
    private WordReader wordReader;

    @Autowired
    private WordFilter wordFilter;

    @Autowired
    private WordWriter wordWriter;

    public FilterWordsProcessor() { }

    public FilterWordsProcessor(WordReader wordReader, WordFilter wordFilter, WordWriter wordWriter) {
        this.wordReader = wordReader;
        this.wordFilter = wordFilter;
        this.wordWriter = wordWriter;
    }
    
    public void processWords() throws IOException {
        List<String> allWords = wordReader.getWordsFromFile();
        List<String> validWords = wordFilter.getFilteredWords(allWords);
        wordWriter.writeWords(validWords);
    }
}
