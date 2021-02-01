package api.topscoreranking.entities.projections;

import api.topscoreranking.entities.Score;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "scorePartial", types = Score.class)
public interface ScoreProjection {

    String getPlayer();

    Date getTime();

    Integer getScore();
}
