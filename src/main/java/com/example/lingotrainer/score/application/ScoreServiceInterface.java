package com.example.lingotrainer.score.application;

import com.example.lingotrainer.score.domain.Score;

public interface ScoreServiceInterface {

    Score findById(long id);

    Score save(Score score);

    Score updateById(Long scoreid, Score score);

    Boolean deleteById(Long scoreid);
}
