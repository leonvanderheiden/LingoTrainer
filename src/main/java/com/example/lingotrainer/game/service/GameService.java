package com.example.lingotrainer.game.service;

import com.example.lingotrainer.game.Game;
import com.example.lingotrainer.game.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getGame(Long id) {
        return gameRepository.findDistinctById(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateById(Long gameid, Game game) {
        Game g = gameRepository.findDistinctById(gameid);

        g.setScore(game.getScore());

        return gameRepository.save(g);
    }

    @Override
    public Boolean deleteById(Long gameid) {
        return gameRepository.deleteDistinctById(gameid);
    }
}
