package api.topscoreranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;

@SpringBootApplication
@EntityScan(basePackageClasses = {TopScoreRankingApiApplication.class, Jsr310Converters.class})
public class TopScoreRankingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopScoreRankingApiApplication.class, args);
	}

}
