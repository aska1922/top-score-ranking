package api.topscoreranking.repository;

import api.topscoreranking.entities.Score;
import api.topscoreranking.repos.ScoresRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration test for repository class (persistence layer).
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/schema-h2.sql", "/data-h2.sql"})
public class ScoresRepositoryTest {

    @Autowired
    private ScoresRepository scoresRepository;

    @Test
    public void testFindById() {
        Optional<Score> res = scoresRepository.findById(1L);
        assertThat(res.get().getScore(), is(5));
        assertThat(res.get().getPlayer(), is("Foobar"));
    }

    @Test
    public void testDeleteById() {
        Optional<Score> res = scoresRepository.findById(4L);
        assertThat(res.get().getScore(), is(2));
        assertThat(res.get().getPlayer(), is("Test"));
        scoresRepository.deleteById(4L);

        Optional<Score> res2 = scoresRepository.findById(4L);
        assertThat(res2, is(Optional.empty()));
    }

    @Test
    public void testFindByPlayer() {
        String player = "Foobar";
        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByPlayer(player, page);
        assertThat(res.getTotalElements(), is(3L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindByTime() throws ParseException {
        String strDate = "2021-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);
        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByTime(date, page);
        assertThat(res.getTotalElements(), is(2L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindByPlayerAndTime() throws ParseException {
        String player = "Foobar";
        String strDate = "2021-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByPlayerAndTime(player, date, page);
        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindAllScoresAfterTime() throws ParseException {
        String strDate = "2021-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findAllScoresAfterTime(date, page);
        assertThat(res.getTotalElements(), is(5L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindAllScoresBeforeTime() throws ParseException {
        String strDate = "2021-01-02";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findAllScoresBeforeTime(date, page);
        assertThat(res.getTotalElements(), is(3L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindByPlayerAndBeforeTime() throws ParseException {
        String player = "Foobar";
        String strDate = "2021-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByPlayerAndBeforeTime(date, player, page);
        assertThat(res.getTotalElements(), is(1L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindByPlayerAndAfterTime() throws ParseException {
        String player = "Foobar";
        String strDate = "2021-01-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(strDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByPlayerAndAfterTime(date, player, page);
        assertThat(res.getTotalElements(), is(3L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindAllScoresBeforeAndAfterTime() throws ParseException {
        String player = "Foobar";
        String strAfterDate = "2021-01-01";
        String strBeforeDate = "2021-01-03";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beforeDate = formatter.parse(strBeforeDate);
        Date afterDate = formatter.parse(strAfterDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findAllScoresBeforeAndAfterTime(beforeDate, afterDate, page);
        assertThat(res.getTotalElements(), is(4L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindByPlayerAndBeforeAndAfterTime() throws ParseException {
        String player = "Foobar";
        String strAfterDate = "2021-01-01";
        String strBeforeDate = "2021-01-03";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date beforeDate = formatter.parse(strBeforeDate);
        Date afterDate = formatter.parse(strAfterDate);

        Pageable page = PageRequest.of(0, 5);

        Page<Score> res = scoresRepository.findByPlayerAndBeforeAndAfterTime(beforeDate, afterDate, player, page);
        assertThat(res.getTotalElements(), is(2L));
        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
        assertThat(res.get().findFirst().get().getScore(), is(5));
    }

    @Test
    public void testFindAvgScoreByPlayer() {
        String player = "Foobar";

        Double res = scoresRepository.findAvgScoreByPlayer(player);
        assertThat(res, is(6.3333));
    }

    @Test
    public void testFindBestScoreAndTimeByPlayer() {
        String player = "Foobar";
        String strDate = "2021-01-01 09:00:00";

        Object[] timeScore = { 7, Timestamp.valueOf(strDate) };

        Object[] res = scoresRepository.findBestScoreAndTimeByPlayer(player);
        assertThat(res[0], is(timeScore));
    }

    @Test
    public void testFindLowestScoreAndTimeByPlayer() {
        String player = "Foobar";
        String strDate = "2021-01-01 09:00:00";

        Object[] timeScore = { 5, Timestamp.valueOf(strDate) };

        Object[] res = scoresRepository.findLowestScoreAndTimeByPlayer(player);
        assertThat(res[0], is(timeScore));
    }


}
