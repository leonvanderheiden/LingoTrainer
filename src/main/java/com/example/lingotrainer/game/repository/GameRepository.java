package com.example.lingotrainer.game.repository;

import com.example.lingotrainer.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByid(Long id);
}
