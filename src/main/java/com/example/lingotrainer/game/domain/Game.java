package com.example.lingotrainer.game.domain;

import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.score.domain.Score;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "score_id", referencedColumnName = "id", nullable = true)
    private Score score;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = true)
    private List<Round> rounds = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.MERGE)
    @JoinColumn(name = "player_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    public Game() { }

    public Game(Long id, Score score) {
        this.id = id;
        this.score = score;
    }

    public Game(Long id, Score score, List<Round> roundList) {
        this.id = id;
        this.score = score;
        this.rounds = roundList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
