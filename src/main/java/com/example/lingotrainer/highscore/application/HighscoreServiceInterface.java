package com.example.lingotrainer.highscore.application;

import com.example.lingotrainer.highscore.domain.Highscore;

public interface HighscoreServiceInterface {
    Highscore findById(long highscoreid);

    Highscore save(Highscore highscore);

    Highscore updateById(Long highscoreid, Highscore highscore);

    Boolean deleteById(Long highscoreid);
}
