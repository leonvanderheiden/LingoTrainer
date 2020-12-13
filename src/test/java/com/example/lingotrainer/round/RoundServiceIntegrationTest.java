package com.example.lingotrainer.round;

import com.example.lingotrainer.round.data.RoundRepository;
import com.example.lingotrainer.round.domain.Round;
import com.example.lingotrainer.round.service.RoundService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoundServiceIntegrationTest {

    @TestConfiguration
    static class RoundServiceIntegrationTestContextConfiguration {

        @Bean
        public RoundService roundService() {
            return new RoundService();
        }
    }

    @Autowired
    private RoundService roundService;

    @MockBean
    private RoundRepository roundRepository;


    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "5 letterwoord";
        Round round = roundService.getRound(3L);

        assertEquals(name, round.getRoundType());
    }

    // write test cases here
}