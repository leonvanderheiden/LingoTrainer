package com.example.lingotrainer.game.service;

import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.game.data.GameRepository;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.player.service.PlayerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerServiceInterface playerService;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game getGameById(Long id) {
        return gameRepository.findDistinctById(id);
    }

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    //Nieuwe game wordt opgeslagen en gelijk toegevoegd aan een player
    @Override
    public Game saveGameToPlayer(Long id, Game game) {
        Player player = playerService.findById(id);
        game.setPlayer(player);

        return gameRepository.save(game);
    }

    @Override
    public Game updateById(Long gameid, Game game) {
        Game g = gameRepository.findDistinctById(gameid);
        g.setScore(game.getScore());
        g.getRounds().clear();
        g.getRounds().addAll(game.getRounds());

        return gameRepository.save(g);
    }

    @Override
    public Boolean deleteById(Long gameid) {
        return gameRepository.deleteDistinctById(gameid);
    }
}
