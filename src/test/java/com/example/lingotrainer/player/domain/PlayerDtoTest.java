package com.example.lingotrainer.player.domain;

import com.example.lingotrainer.game.domain.Game;
import com.example.lingotrainer.highscore.domain.Highscore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDtoTest {
    @Test
    @DisplayName("Set id of a player object")
    public void setIdTest() {
        long id = 1000;
        PlayerDto player = new PlayerDto();
        player.setId(id);
        assertEquals(player.getId(), id);
    }

    @Test
    @DisplayName("Get id of a player object")
    public void getIdTest() throws NoSuchFieldException, IllegalAccessException {

        PlayerDto player = new PlayerDto();
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
        PlayerDto player = new PlayerDto();
        player.setName(name);
        assertEquals(player.getName(), name);
    }

    @Test
    @DisplayName("Get name of a player object")
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        PlayerDto player = new PlayerDto();
        final Field field = player.getClass().getDeclaredField("name");
        field.setAccessible(true);
        field.set(player, "name");

        String result = player.getName();

        assertEquals("name", result);
    }

    @Test
    @DisplayName("Set highscore of a player")
    public void setHighscoreTest() {
        Highscore highscore = new Highscore(1L, 500L);
        PlayerDto playerObj = new PlayerDto();

        playerObj.setHighscore(highscore);

        assertEquals(playerObj.getHighscore(), highscore);
    }

    @Test
    @DisplayName("Get highscore of a player")
    public void getHighscoreTest() throws NoSuchFieldException, IllegalAccessException {
        PlayerDto playerObj = new PlayerDto();
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
        PlayerDto playerObj = new PlayerDto();

        playerObj.setGames(gameList);

        assertEquals(playerObj.getGames(), gameList);
    }

    @Test
    @DisplayName("Get games of a player")
    public void getGamesTest() throws NoSuchFieldException, IllegalAccessException {
        PlayerDto playerObj = new PlayerDto();
        List<Game> gameList = null;
        final Field field = playerObj.getClass().getDeclaredField("games");
        field.setAccessible(true);
        field.set(playerObj, gameList);

        List<Game> result = playerObj.getGames();

        assertEquals(gameList, result);
    }
}
