package com.example.lingotrainer.highscore.service;

import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.score.domain.Score;

public interface HighscoreServiceInterface {
    Highscore findById(long highscoreid);

    Highscore save(Highscore highscore);

    Highscore updateById(Long highscoreid, Highscore highscore);

    Boolean deleteById(Long highscoreid);
}
