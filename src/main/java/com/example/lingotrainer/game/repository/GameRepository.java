package com.example.lingotrainer.game.repository;

import com.example.lingotrainer.game.Game;
import com.example.lingotrainer.round.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Transactional
    Boolean deleteDistinctById(Long gameid);

    @Transactional
    Game findDistinctById(Long gameid);
}
