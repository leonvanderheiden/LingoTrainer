package com.example.lingotrainer.highscore.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "highscore")
public class Highscore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long highscore;

    public Highscore() { }

    public Highscore(Long highscore) {
        this.highscore = highscore;
    }

    public Highscore(Long id, Long highscore) { this.id = id; this.highscore = highscore; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHighscore() {
        return highscore;
    }

    public void setHighscore(Long highscore) {
        this.highscore = highscore;
    }
}
