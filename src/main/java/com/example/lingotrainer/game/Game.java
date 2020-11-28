package com.example.lingotrainer.game;

import com.example.lingotrainer.player.Player;
import com.example.lingotrainer.round.Round;
import com.example.lingotrainer.score.Score;
import com.example.lingotrainer.word.Word;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game")
    private List<Round> rounds;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "score_id", referencedColumnName = "id", nullable = true)
    private Score score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = true)
    private Word word;

    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Player player;
}
