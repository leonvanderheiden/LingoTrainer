package com.example.lingotrainer.game.data;

import com.example.lingotrainer.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
