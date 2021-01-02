package com.example.lingotrainer.filterwords.domain;

import java.util.List;

public interface WordFilter {

    List<String> getFilteredWords(List<String> AllWords);

    boolean isWordValid(String word);
}
