package com.example.lingotrainer.player.domain;

import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.highscore.domain.Highscore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    @DisplayName("Set id of a player object")
    public void setIdTest() {
        long id = 1000;
        Player player = new Player();
        player.setId(id);
        assertEquals(player.getId(), id);
    }

    @Test
    @DisplayName("Get id of a player object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        Player player = new Player();
        final Field field = player.getClass().getDeclaredField("id");
        field.setAccessible(true);
        field.set(player, 1L);

        long result = player.getId();

        assertEquals(1L, result);
    }


    @Test
    @DisplayName("Set password of a player object")
    public void setNameTest() {
        String name = "test";
        Player player = new Player();
        player.setName(name);
        assertEquals(player.getName(), name);
    }

    @Test
    @DisplayName("Get name of a player object")
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player();
        final Field field = player.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(player, "name");

        String result = player.getName();

        assertEquals("name", result);
    }


    @Test
    @DisplayName("Set password of a player object")
    public void setPasswordTest() {
        String password = "pass";
        Player player = new Player();
        player.setPassword(password);
        assertEquals(player.getPassword(), password);
    }

    @Test
    @DisplayName("Get password of a player object")
    public void getPasswordTest() throws NoSuchFieldException, IllegalAccessException {
        Player player = new Player();
        final Field field = player.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(player, "pass");

        String result = player.getPassword();

        assertEquals("pass", result);
    }

    @Test
    @DisplayName("Set highscore of a player")
    public void setHighscoreTest() {
        Highscore highscore = new Highscore(1L, 500L);
        Player playerObj = new Player();

        playerObj.setHighscore(highscore);

        assertEquals(playerObj.getHighscore(), highscore);
    }

    @Test
    @DisplayName("Get highscore of a player")
    public void getHighscoreTest() throws NoSuchFieldException, IllegalAccessException {
        Player playerObj = new Player();
        Highscore highscore = new Highscore(1L, 500L);
        final Field field = playerObj.getClass().getDeclaredField("highscore");
        field.setAccessible(true);
        field.set(playerObj, highscore);

        Highscore result = playerObj.getHighscore();

        assertEquals(highscore, result);
    }

    @Test
    @DisplayName("Set gamelist of a player")
    public void setGamesTest() {
        List<Game> gameList = null;
        Player playerObj = new Player();

        playerObj.setGames(gameList);

        assertEquals(playerObj.getGames(), gameList);
    }

    @Test
    @DisplayName("Get games of a player")
    public void getGamesTest() throws NoSuchFieldException, IllegalAccessException {
        Player playerObj = new Player();
        List<Game> gameList = null;
        final Field field = playerObj.getClass().getDeclaredField("games");
        field.setAccessible(true);
        field.set(playerObj, gameList);

        List<Game> result = playerObj.getGames();

        assertEquals(gameList, result);
    }
}
