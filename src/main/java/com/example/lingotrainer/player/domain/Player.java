package com.example.lingotrainer.player.domain;

import com.example.lingotrainer.game.domain.Game;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player", uniqueConstraints=
@UniqueConstraint(columnNames = {"name"}))
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "highscore_id", referencedColumnName = "id", nullable = true)
    //private Highscore highscore;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String password;

    @OneToMany(mappedBy = "player")
    private List<Game> games;

    public Player() {}

    public Player(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}