package com.example.lingotrainer.Word.service;

import java.io.FileNotFoundException;
import java.util.List;

public interface WordServiceInterace {
    List<String> getWords(long length) throws FileNotFoundException;
}

