package com.example.lingotrainer.score.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private long score;

    public Score() { }

    public Score(Long id, long score) {
        this.id = id;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
