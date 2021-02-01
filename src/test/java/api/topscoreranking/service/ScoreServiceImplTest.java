package api.topscoreranking.service;

import api.topscoreranking.entities.Score;
import api.topscoreranking.repos.ScoresRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * JUnit (Mock) test for Score Service class (service layer).
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ScoreServiceImplTest {

    @InjectMocks
    private ScoreServiceImpl scoreService;

    @Mock
    private ScoresRepository scoresRepository;

    @Mock
    private Pageable pageable;

    @Before
    public void setUp() {
        pageable = PageRequest.of(0, 5);
    }

    @Test
    public void testGetScoreByName() {

        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByPlayer(anyString(), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByPlayer(player, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
    }

    @Test
    public void testGetScoreByTime() {

        Date time = new Date();
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByTime(time, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoreByPlayerAndTime() {

        String player = "foobar";
        Date time = new Date();
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByPlayerAndTime(eq(player), eq(time), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByPlayerAndTime(player, time, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoresAfterTime() {

        Date time = new Date();
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findAllScoresAfterTime(eq(time), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoresAfterTime(time, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoreBeforeTime() {

        Date time = new Date();
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findAllScoresBeforeTime(any(Date.class), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoresBeforeTime(time, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoreByPlayerAndBeforeTime() {

        Date time = new Date();
        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByPlayerAndBeforeTime(eq(time), eq(player), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByPlayerAndBeforeTime(time, player, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoreByPlayerAndAfterTime() {

        Date time = new Date();
        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByPlayerAndAfterTime(eq(time), eq(player), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByPlayerAndAfterTime(time, player, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoresBeforeAndAfterTime() {

        Date time = new Date();
        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findAllScoresBeforeAndAfterTime(eq(time), eq(time), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoresBeforeAndAfterTime(time, time, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetScoreByPlayerAndBeforeAndAfterTime() {

        Date time = new Date();
        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        Page<Score> pgScore = new PageImpl<>(Collections.singletonList(score));
        when(scoresRepository.findByPlayerAndBeforeAndAfterTime(eq(time), eq(time), eq(player), any(Pageable.class))).thenReturn(pgScore);
        Page<Score> res = scoreService.getScoreByPlayerAndBeforeAndAfterTime(time, time, player, pageable);

        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(2));
        assertThat(res.get().findFirst().get().getTime(), is(time));
    }

    @Test
    public void testGetAvgScoreByPlayer() {

        Date time = new Date();
        String player = "foobar";
        Score score = new Score();
        score.setPlayer("foobar");
        score.setScore(2);
        score.setTime(time);
        when(scoresRepository.findAvgScoreByPlayer(eq(player))).thenReturn(2.0);
        double res = scoreService.getAvgScoreByPlayer(player);

        assertThat(res, is(2.0));
    }

    @Test
    public void testGetBestScoreAndTimeByPlayer() {

        Date time = new Date();
        String player = "foobar";
        Object[] timeScore = { 2, time };
        when(scoresRepository.findBestScoreAndTimeByPlayer(player)).thenReturn(timeScore);
        Object[] res = scoreService.getBestScoreAndTimeByPlayer(player);

        assertThat(res[0], is(2));
        assertThat(res[1], is(time));
    }

    @Test
    public void testGetLowestScoreAndTimeByPlayer() {

        Date time = new Date();
        String player = "foobar";
        Object[] timeScore = { 2, time };
        when(scoresRepository.findLowestScoreAndTimeByPlayer(player)).thenReturn(timeScore);
        Object[] res = scoreService.getLowestScoreAndTimeByPlayer(player);

        assertThat(res[0], is(2));
        assertThat(res[1], is(time));
    }

}
