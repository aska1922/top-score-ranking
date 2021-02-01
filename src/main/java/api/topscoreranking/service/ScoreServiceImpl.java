package api.topscoreranking.service;

import api.topscoreranking.entities.Score;
import api.topscoreranking.repos.ScoresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final ScoresRepository scoresRepository;

    @Override
    public Score addScore(Score score) {
        return scoresRepository.save(score);
    }

    @Override
    public Score getById(Long id) {
        return scoresRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        scoresRepository.deleteById(id);
    }

    @Override
    public Page<Score> getScoreByPlayer(String player, Pageable page) {
        return scoresRepository.findByPlayer(player, page);
    }

    @Override
    public Page<Score> getScoreByTime(Date time, Pageable page) {
        return scoresRepository.findByTime(time, page);
    }

    @Override
    public Page<Score> getScoreByPlayerAndTime(String player, Date time, Pageable page) {
        return scoresRepository.findByPlayerAndTime(player, time, page);
    }

    @Override
    public Page<Score> getScoresAfterTime(Date time, Pageable page) {
        return scoresRepository.findAllScoresAfterTime(time, page);
    }

    @Override
    public Page<Score> getScoresBeforeTime(Date time, Pageable page) {
        return scoresRepository.findAllScoresBeforeTime(time, page);
    }

    @Override
    public Page<Score> getScoreByPlayerAndBeforeTime(Date time, String player, Pageable page) {
        return scoresRepository.findByPlayerAndBeforeTime(time, player, page);
    }

    @Override
    public Page<Score> getScoreByPlayerAndAfterTime(Date time, String player, Pageable page) {
        return scoresRepository.findByPlayerAndAfterTime(time, player, page);
    }

    @Override
    public Page<Score> getScoresBeforeAndAfterTime(Date beforeTime, Date afterTime, Pageable page) {
        return scoresRepository.findAllScoresBeforeAndAfterTime(beforeTime, afterTime, page);
    }

    @Override
    public Page<Score> getScoreByPlayerAndBeforeAndAfterTime(Date beforeTime, Date afterTime, String player, Pageable page) {
        return scoresRepository.findByPlayerAndBeforeAndAfterTime(beforeTime, afterTime, player, page);
    }

    @Override
    public double getAvgScoreByPlayer(String player) {
        return scoresRepository.findAvgScoreByPlayer(player);
    }

    @Override
    public Object[] getBestScoreAndTimeByPlayer(String player) {
        return scoresRepository.findBestScoreAndTimeByPlayer(player);
    }

    @Override
    public Object[] getLowestScoreAndTimeByPlayer(String player) {
        return scoresRepository.findLowestScoreAndTimeByPlayer(player);
    }
}
