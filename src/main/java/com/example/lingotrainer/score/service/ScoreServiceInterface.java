package com.example.lingotrainer.score.service;

import com.example.lingotrainer.score.Score;

public interface ScoreServiceInterface {

    Score getScore(long id);

    Score save(Score score);

    Score updateById(Long scoreid, Score score);

    Boolean deleteById(Long scoreid);
}
