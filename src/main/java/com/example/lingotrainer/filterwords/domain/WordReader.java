package com.example.lingotrainer.filterwords.domain;

import java.io.FileNotFoundException;
import java.util.List;

public interface WordReader {
    List<String> getWordsFromFile() throws FileNotFoundException;
}
