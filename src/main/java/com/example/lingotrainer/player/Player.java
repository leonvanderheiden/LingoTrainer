package com.example.lingotrainer.player;

import com.example.lingotrainer.game.Game;
import com.example.lingotrainer.highscore.Highscore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "highscore_id", referencedColumnName = "id", nullable = true)
    private Highscore highscore;

    /*@OneToMany(mappedBy = "player")
    private List<Game> games;*/

    @NotNull
    private String name;

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
