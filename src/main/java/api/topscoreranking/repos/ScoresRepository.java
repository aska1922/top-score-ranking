package api.topscoreranking.repos;

import api.topscoreranking.entities.Score;
import api.topscoreranking.entities.projections.ScoreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@RepositoryRestResource(excerptProjection =  ScoreProjection.class)
public interface ScoresRepository extends PagingAndSortingRepository<Score, Long> {

    Page<Score> findByPlayer(@Param("player") String player, Pageable pageable);

    @Query("select s from Score s where s.time = :time")
    Page<Score> findByTime(@Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, Pageable pageable);

    Page<Score> findByPlayerAndTime(@Param("player") String player,
                                    @Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                    Pageable pageable);

    @Query("select s from Score s where s.time >= :time")
    Page<Score> findAllScoresAfterTime(@Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                       Pageable pageable);

    @Query("select s from Score s where s.time <= :time")
    Page<Score> findAllScoresBeforeTime(@Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                       Pageable pageable);

    @Query("select s from Score s where s.time <= :time and s.player = :player")
    Page<Score> findByPlayerAndBeforeTime(@Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                          @Param("player") String player,
                                        Pageable pageable);

    @Query("select s from Score s where s.time >= :time and s.player = :player")
    Page<Score> findByPlayerAndAfterTime(@Param("time") @DateTimeFormat(pattern = "yyyy-MM-dd") Date time,
                                         @Param("player") String player,
                                          Pageable pageable);

    @Query("select s from Score s where s.time >= :afterTime and s.time <= :beforeTime")
    Page<Score> findAllScoresBeforeAndAfterTime(@Param("beforeTime")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeTime,
                                                @Param("afterTime")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime,
                                                Pageable pageable);

    @Query("select s from Score s where s.time >= :afterTime and s.time <= :beforeTime and s.player = :player")
    Page<Score> findByPlayerAndBeforeAndAfterTime(@Param("beforeTime")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") Date beforeTime,
                                                @Param("afterTime")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") Date afterTime,
                                                  @Param("player") String player,
                                                  Pageable pageable);

    @Query(value = "select AVG(s.score) as average from Score s where s.player = :player", nativeQuery = true)
    double findAvgScoreByPlayer(@Param("player") String player);

    @Query(value = "select max(s.score) as best, s.time as time from Score s where s.player = :player", nativeQuery = true)
    Object[] findBestScoreAndTimeByPlayer(@Param("player") String player);

    @Query(value = "select min(s.score) as score, s.time as time from Score s where s.player = :player", nativeQuery = true)
    Object[] findLowestScoreAndTimeByPlayer(@Param("player") String player);

    @Override
    void deleteById(Long id);
}
