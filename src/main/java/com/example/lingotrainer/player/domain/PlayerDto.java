package com.example.lingotrainer.player.domain;

import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.highscore.domain.Highscore;

import java.util.List;

public class PlayerDto {
    private Long id;

    private String name;

    private List<Game> games;

    private Highscore highscore;

    public PlayerDto(Long id, String name, Highscore highscore) {
        this.id = id;
        this.name = name;
        this.highscore = highscore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Highscore getHighscore() {
        return highscore;
    }

    public void setHighscore(Highscore highscore) {
        this.highscore = highscore;
    }
}
