package com.example.lingotrainer.player.data;

import com.example.lingotrainer.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Transactional
    Player findByNameIs(String name);

    @Transactional
    Boolean existsByName(String name);
}
