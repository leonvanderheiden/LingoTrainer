package com.example.lingotrainer.game.application;

import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.game.data.GameRepository;
import com.example.lingotrainer.game.exceptions.GameNotFoundException;
import com.example.lingotrainer.player.application.PlayerService;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.player.application.PlayerServiceInterface;
import com.example.lingotrainer.score.exceptions.ScoreNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService implements GameServiceInterface {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerServiceInterface playerService;

    public GameService(GameRepository gameRepository, PlayerServiceInterface playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    @Override
    public Game findById(Long gameid) {
        return gameRepository.findById(gameid).orElseThrow(() -> new GameNotFoundException(gameid));
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
        Game g = gameRepository.findById(gameid).orElseThrow(() -> new GameNotFoundException(gameid));
        //g.setScore(game.getScore());
        g.getRounds().clear();
        g.getRounds().addAll(game.getRounds());

        return gameRepository.save(g);
    }

    @Override
    public Boolean deleteById(Long gameid) {
        Game g = gameRepository.findById(gameid).orElseThrow(() -> new ScoreNotFoundException(gameid));
        if (g != null) {
            gameRepository.deleteById(g.getId());
        }
        return (g != null);
    }
}
