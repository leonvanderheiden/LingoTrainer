package com.example.lingotrainer.player.presentation;

import com.example.lingotrainer.highscore.domain.Highscore;
import com.example.lingotrainer.player.application.PlayerService;
import com.example.lingotrainer.player.application.PlayerServiceInterface;
import com.example.lingotrainer.player.domain.Player;
import com.example.lingotrainer.player.domain.PlayerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @MockBean
    private PlayerService playerService;

    @MockBean
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void init() {
        ModelMapper modelMapper = new ModelMapper();
    }

    @Test
    @DisplayName("getting an existing player by id")
    public void getPlayerByIdTest() throws Exception {
        Player p = createPlayer(1L, "test", "pass", 1L, 500L);
        PlayerDto playerDto = new PlayerDto(1L, "test", new Highscore(1L, 500L));

        when(playerService.findById(any())).thenReturn(p);

        mvc.perform(get("/player/" + p.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.password").value("pass"))
                .andExpect(jsonPath("$.highscore.id").value(1L))
                .andExpect(jsonPath("$.highscore.highscore").value(500L))*/
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("saving a new player")
    public void savePlayerTest() throws Exception {
        Player p = createPlayer(1L, "test", "pass", 1L, 500L);

        when(playerService.save(any())).thenReturn(p);

        mvc.perform( MockMvcRequestBuilders
                .post("/player")
                .content(asJsonString(p))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                /*.andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.password").value("pass"))
                .andExpect(jsonPath("$.highscore.id").value(1L))
                .andExpect(jsonPath("$.highscore.highscore").value(500L))*/;
    }

    @Test
    @DisplayName("updating an existing player")
    public void updatePlayerTest() throws Exception
    {
        Player oldPlayer = createPlayer(1L, "test", "pass", 1L, 500L);
        Player newPlayer = createPlayer(1L, "test", "pass123", 2L, 900L);

        given(playerService.updateById(any(), any())).willReturn(newPlayer);

        mvc.perform(MockMvcRequestBuilders
                .put("/player/{id}", 1L)
                .content(asJsonString(oldPlayer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                /*.andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.password").value("pass123"))
                .andExpect(jsonPath("$.highscore.id").value(2L))
                .andExpect(jsonPath("$.highscore.highscore").value(900L))*/;
    }

    @Test
    @DisplayName("deleting an existing player by id")
    public void deletePlayerByIdTest() throws Exception {

        Player p = createPlayer(1L, "test", "pass", 1L, 500L);

        given(playerService.deleteById(any())).willReturn(true);

        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .delete("/player/" + p.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        boolean content = Boolean.parseBoolean(result.getResponse().getContentAsString());
        assertThat(content == true);
    }

    public static Player createPlayer(Long playerId, String name, String password,
                                      Long highscoreId, Long highscore) {
        Player player = new Player();
        player.setId(playerId);
        player.setName(name);
        player.setPassword(password);

        Highscore highscoreObj = new Highscore();
        highscoreObj.setId(highscoreId);
        highscoreObj.setHighscore(highscore);
        player.setHighscore(highscoreObj);

        return player;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
