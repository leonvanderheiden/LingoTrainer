package com.example.lingotrainer.filterwords.presentation;

import com.example.lingotrainer.filterwords.application.FilterWordsProcessor;
import com.example.lingotrainer.filterwords.data.FileWordReader;
import com.example.lingotrainer.filterwords.domain.FileWordWriter;
import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class ProcessWordsCommand {
    @Autowired
    public FilterWordsProcessor filterWordsProcessor;


    public ProcessWordsCommand() {
        filterWordsProcessor = new FilterWordsProcessor(new FileWordReader(), new LingoWordFilter(), new FileWordWriter());
    }

    public ProcessWordsCommand(FilterWordsProcessor filterWordsProcessor) {
        this.filterWordsProcessor = filterWordsProcessor;
    }

    public void process() throws FileNotFoundException {
        filterWordsProcessor.processWords();
    }
}
