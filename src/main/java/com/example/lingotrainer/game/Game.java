package com.example.lingotrainer.game;

import com.example.lingotrainer.player.Player;
import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.score.Score;
import com.example.lingotrainer.word.Word;
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
    @JoinColumn(name = "round_id", referencedColumnName = "id", nullable = true)
    private List<Round> rounds = new ArrayList<>();

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = true)
    private Word word;*/

}
