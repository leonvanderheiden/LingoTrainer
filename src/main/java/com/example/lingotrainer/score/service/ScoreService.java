package com.example.lingotrainer.score.service;

import com.example.lingotrainer.score.Score;
import com.example.lingotrainer.score.repository.ScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreService implements ScoreServiceInterface {
    private ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Score getScore(long id) {
        return scoreRepository.findDistinctById(id);
    }

    @Override
    public Score save(Score score) {

        return scoreRepository.save(score);
    }

    @Override
    public Score updateById(Long scoreid, Score score) {
        Score  s = scoreRepository.findDistinctById(scoreid);

        s.setScore(score.getScore());

        return scoreRepository.save(s);
    }

    @Override
    public Boolean deleteById(Long scoreid) {
        return scoreRepository.deleteDistinctById(scoreid);
    }
}
