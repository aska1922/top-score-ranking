package api.topscoreranking.controller;

import api.topscoreranking.entities.Score;
import api.topscoreranking.service.ScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MVC JUnit (mock) test for controller class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ScoreController.class)
@AutoConfigureMockMvc
public class ScoreControllerTest {

    @MockBean
    private ScoreService scoreService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSetScore() throws Exception {
        Score score = new Score();
        score.setScore(2);
        score.setPlayer("foobar");

        mockMvc.perform(post("/scores/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(score)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetScoreByName() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByPlayer(anyString(), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/player/{player}", "foobar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoreById() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);

        when(scoreService.getById(anyLong())).thenReturn(score);

        mockMvc.perform(get("/scores/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testDeleteScore() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        mockMvc.perform(delete("/scores/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testGetScoreByTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/time/{time}", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoreByPlayerAndTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByPlayerAndTime(anyString(), any(Date.class), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/player/{player}/time/{time}", "foobar", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoresAfterTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoresAfterTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/afterTime/{time}", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoresBeforeTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoresBeforeTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/beforeTime/{time}", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoreByPlayerAndBeforeTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByPlayerAndBeforeTime(any(Date.class), anyString(), any(Pageable.class)))
                .thenReturn(pgScore);

        mockMvc.perform(get("/scores/player/{player}/beforeTime/{time}", "foobar", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoreByPlayerAndAfterTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByPlayerAndAfterTime(any(Date.class), anyString(), any(Pageable.class)))
                .thenReturn(pgScore);

        mockMvc.perform(get("/scores/player/{player}/afterTime/{time}", "foobar", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoresBeforeAndAfterTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoresBeforeTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/beforeTime/{time}", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetScoreByPlayerAndBeforeAndAfterTime() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));

        when(scoreService.getScoreByPlayerAndBeforeAndAfterTime(any(Date.class), any(Date.class), anyString(),
                any(Pageable.class))).thenReturn(pgScore);

        mockMvc.perform(get("/scores/player/{player}/beforeTime/{beforeTime}/afterTime/{afterTime}",
                "foobar", "2020-01-03", "2020-01-01")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("foobar")))
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetAvgScoreByPlayer() throws Exception {
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);

        when(scoreService.getAvgScoreByPlayer(anyString())).thenReturn(2.0);

        mockMvc.perform(get("/scores/averageScore/player/{player}", "foobar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2.0")));

    }

    @Test
    public void testGetBestScoreAndTimeByPlayer() throws Exception {
        String strDate = "2020-01-01";
        Date time = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        Object[] timeScore = { 2, time };

        when(scoreService.getBestScoreAndTimeByPlayer(anyString())).thenReturn(timeScore);

        mockMvc.perform(get("/scores/bestScore/player/{player}", "foobar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));

    }

    @Test
    public void testGetLowestScoreAndTimeByPlayer() throws Exception {
        String strDate = "2020-01-01";
        Date time = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        Object[] timeScore = { 2, time };

        when(scoreService.getLowestScoreAndTimeByPlayer(anyString())).thenReturn(timeScore);

        mockMvc.perform(get("/scores/lowestScore/player/{player}", "foobar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));

    }
}
