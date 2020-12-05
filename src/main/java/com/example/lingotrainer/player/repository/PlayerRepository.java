package com.example.lingotrainer.player.repository;

import com.example.lingotrainer.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Transactional
    Player findDistinctById(Long playerid);

    @Transactional
    Player findByNameIs(String name);

    @Transactional
    Boolean deleteDistinctById(Long playerid);
}
