package com.example.lingotrainer.highscore.application;

import com.example.lingotrainer.highscore.data.HighscoreRepository;
import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.score.exceptions.ScoreNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HighscoreService implements HighscoreServiceInterface {

    private HighscoreRepository highscoreRepository;

    public HighscoreService(HighscoreRepository highscoreRepository) {
        this.highscoreRepository = highscoreRepository;
    }

    @Override
    public Highscore findById(long highscoreid) {
        return highscoreRepository.findById(highscoreid).orElseThrow(() -> new ScoreNotFoundException(highscoreid));
    }

    @Override
    public Highscore save(Highscore highscore) {
        return highscoreRepository.save(highscore);
    }

    @Override
    public Highscore updateById(Long highscoreid, Highscore highscore) {
        Highscore s = highscoreRepository.findById(highscoreid).orElseThrow(() -> new ScoreNotFoundException(highscoreid));
        s.setHighscore(highscore.getHighscore());

        return highscoreRepository.save(s);
    }

    @Override
    public Boolean deleteById(Long highscoreid) {
        Highscore h = highscoreRepository.findById(highscoreid).orElseThrow(() -> new ScoreNotFoundException(highscoreid));
        if (h != null) {
            highscoreRepository.deleteById(h.getId());
        }
        return (h != null);
    }
}
