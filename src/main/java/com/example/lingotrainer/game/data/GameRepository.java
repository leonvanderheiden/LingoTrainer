package com.example.lingotrainer.game.data;

import com.example.lingotrainer.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Transactional
    Boolean deleteDistinctById(Long gameid);

    @Transactional
    Game findDistinctById(Long gameid);
}
