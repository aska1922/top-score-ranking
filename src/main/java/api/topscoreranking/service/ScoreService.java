package api.topscoreranking.service;

import api.topscoreranking.entities.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ScoreService {

    Score addScore(Score score);

    Score getById(Long id);

    void deleteById(Long id);

    Page<Score> getScoreByPlayer(String player, Pageable page);

    Page<Score> getScoreByTime(Date time, Pageable page);

    Page<Score> getScoreByPlayerAndTime(String player, Date time, Pageable page);

    Page<Score> getScoresAfterTime(Date time, Pageable page);

    Page<Score> getScoresBeforeTime(Date time, Pageable page);

    Page<Score> getScoreByPlayerAndBeforeTime(Date time, String player, Pageable page);

    Page<Score> getScoreByPlayerAndAfterTime(Date time, String player, Pageable page);

    Page<Score> getScoresBeforeAndAfterTime(Date beforeTime, Date afterTime, Pageable page);

    Page<Score> getScoreByPlayerAndBeforeAndAfterTime(Date beforeTime, Date afterTime, String player, Pageable page);

    double getAvgScoreByPlayer(String player);

    Object[] getBestScoreAndTimeByPlayer(String player);

    Object[] getLowestScoreAndTimeByPlayer(String player);
}
