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
