package com.example.lingotrainer.filterwords.presentation;

import com.example.lingotrainer.filterwords.application.FilterWordsProcessor;
import com.example.lingotrainer.filterwords.data.FileWordReader;
import com.example.lingotrainer.filterwords.data.FileWordWriter;
import com.example.lingotrainer.filterwords.domain.LingoWordFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ProcessWordsCommand implements InitializingBean {
    @Autowired
    public FilterWordsProcessor filterWordsProcessor;

    public ProcessWordsCommand(FilterWordsProcessor filterWordsProcessor) {
        this.filterWordsProcessor = filterWordsProcessor;
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        filterWordsProcessor.processWords();
    }
}
