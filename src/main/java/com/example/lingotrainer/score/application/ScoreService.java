package com.example.lingotrainer.score.application;

import com.example.lingotrainer.score.exceptions.ScoreNotFoundException;
import com.example.lingotrainer.score.domain.Score;
import com.example.lingotrainer.score.data.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService implements ScoreServiceInterface {
    @Autowired
    private ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public ScoreService() {

    }

    @Override
    public Score findById(long scoreid) {
        return scoreRepository.findById(scoreid).orElseThrow(() -> new ScoreNotFoundException(scoreid));
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public Score updateById(Long scoreid, Score score) {
        Score  s = scoreRepository.findById(scoreid).orElseThrow(() -> new ScoreNotFoundException(scoreid));
        s.setScore(score.getScore());

        return scoreRepository.save(s);
    }

    @Override
    public Boolean deleteById(Long scoreid) {
        return scoreRepository.deleteDistinctById(scoreid);
    }
}
