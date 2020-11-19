package com.example.lingotrainer.word.service;

import java.io.FileNotFoundException;
import java.util.List;

public interface WordServiceInterace {
    List<String> getWords(long length) throws FileNotFoundException;
}

