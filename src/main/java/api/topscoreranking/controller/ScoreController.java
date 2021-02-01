package api.topscoreranking.controller;

import api.topscoreranking.entities.Score;
import api.topscoreranking.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Controller class for score service.
 */
@RestController
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping(value = "/scores")
    private ResponseEntity<Score> addScore(@Valid @RequestBody Score score) {
        return ResponseEntity.ok().body(this.scoreService.addScore(score));
    }

    @GetMapping(value = "/scores/{id}")
    public Score getScoreById(@PathVariable Long id) {
        return scoreService.getById(id);
    }

    @DeleteMapping(value = "/scores/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable Long id) {
        scoreService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/scores/player/{player}")
    public Page<Score> getScoreByPlayer(@PathVariable String player, Pageable pageable) {
        return scoreService.getScoreByPlayer(player, pageable);
    }

    @GetMapping(value = "/scores/time/{time}")
    public Page<Score> getScoreByTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, 
                                      Pageable pageable) {
        return scoreService.getScoreByTime(time, pageable);
    }

    @GetMapping(value = "/scores/player/{player}/time/{time}")
    public Page<Score> getScoreByPlayerAndTime(@PathVariable String player, 
                                               @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                               Pageable pageable) {
        return scoreService.getScoreByPlayerAndTime(player, time, pageable);
    }

    @GetMapping(value = "/scores/afterTime/{time}")
    public Page<Score> getScoresAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                          Pageable pageable) {
        return scoreService.getScoresAfterTime(time, pageable);
    }

    @GetMapping(value = "/scores/beforeTime/{time}")
    public Page<Score> getScoresBeforeTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, 
                                           Pageable pageable) {
        return scoreService.getScoresBeforeTime(time, pageable);
    }

    @GetMapping(value = "/scores/player/{player}/beforeTime/{time}")
    public Page<Score> getScoreByPlayerAndBeforeTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, Pageable pageable,
                                                     @PathVariable String player) {
        return scoreService.getScoreByPlayerAndBeforeTime(time, player, pageable);
    }

    @GetMapping(value = "/scores/player/{player}/afterTime/{time}")
    public Page<Score> getScoreByPlayerAndAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                                    Pageable pageable,
                                                    @PathVariable String player) {
        return scoreService.getScoreByPlayerAndAfterTime(time, player, pageable);
    }

    @GetMapping(value = "/scores/beforeTime/{beforeTime}/afterTime/{afterTime}")
    public Page<Score> getScoresBeforeAndAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeTime,
                                                   @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime,
                                                   Pageable pageable) {
        return scoreService.getScoresBeforeAndAfterTime(beforeTime, afterTime, pageable);
    }

    @GetMapping(value = "/scores/player/{player}/beforeTime/{beforeTime}/afterTime/{afterTime}")
    public Page<Score> getScoreByPlayerAndBeforeAndAfterTime(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeTime,
                                                             @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime,
                                                             @PathVariable String player, Pageable pageable) {
        return scoreService.getScoreByPlayerAndBeforeAndAfterTime(beforeTime, afterTime, player, pageable);
    }

    @GetMapping(value = "/scores/averageScore/player/{player}")
    public double getAvgScoreByPlayer(@PathVariable String player) {
        return scoreService.getAvgScoreByPlayer(player);
    }

    @GetMapping(value = "/scores/bestScore/player/{player}")
    public Object[] getBestScoreAndTimeByPlayer(@PathVariable String player) {
        return scoreService.getBestScoreAndTimeByPlayer(player);
    }

    @GetMapping(value = "/scores/lowestScore/player/{player}")
    public Object[] getLowestScoreAndTimeByPlayer(@PathVariable String player) {
        return scoreService.getLowestScoreAndTimeByPlayer(player);
    }

}
