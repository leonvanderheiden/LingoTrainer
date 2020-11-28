package com.example.lingotrainer.player.repository;

import com.example.lingotrainer.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByid(Long id);
}
