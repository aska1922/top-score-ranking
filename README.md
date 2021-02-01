# Top Ranking Score API

### About
This API will be used to keep scores for a certain game for a group of player.

### Pre-requisite

1. Install any DB instance locally of your choice, for this instance I am using MySQL.
2. Configure settings in `application.properties` for datasource:
As for datasource URL, you can make your own but for reference below is my setting for only URL. 
(Set username and password based on your settings.)
```
spring.datasource.url=jdbc:mysql://localhost:3306/scores?useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=<insert username of DB>
spring.datasource.password=<insert your DB password here>
```
3. Create the schema on DB (ex.: MySQL)
```
CREATE TABLE score
(
  ID  	int        NOT NULL AUTO_INCREMENT,
  CREATED    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PLAYER      varchar(256) NOT NULL,
  SCORE   int unsigned,
  TIME    datetime        NOT NULL,
  PRIMARY KEY (ID)
);
```

### How to build

1. Open a terminal of your choice in the top-level directory of project.
2. `gradle assemble clean`
3. `gradle build`
4. `gradle bootRun`

### Calling API
#### Endpoints & Detail
1. Add/Post score: http://localhost:8080/top-score-ranking/scores/
2. Get score with player ID: (GET Method) http://localhost:8080/top-score-ranking/scores/{id}
3. Delete by ID: (DELETE Method) http://localhost:8080/top-score-ranking/scores/{id}
4. Get score with time: http://localhost:8080/top-score-ranking//scores/time/{time}
5. Get score with player name: http://localhost:8080/top-score-ranking/scores/player/{player}
6. Get score with player name and time: http://localhost:8080/top-score-ranking/scores/player/{player}/time/{time}
7. Get score after a time: http://localhost:8080/top-score-ranking/scores/afterTime/{time}
8. Get score before a time: http://localhost:8080/top-score-ranking/scores/beforeTime/{time}
9. Get score with player name and before a time: http://localhost:8080/top-score-ranking/scores/player/{player}/beforeTime/{time}
10. Get score with player name and after a time: http://localhost:8080/top-score-ranking/scores/player/{player}/afterTime/{time}
11. Get score between a before and after time: http://localhost:8080/top-score-ranking/scores/beforeTime/{beforeTime}/afterTime/{afterTime}
12. Get score of a player between a before and after time: http://localhost:8080/top-score-ranking/scores/player/{player}/beforeTime/{beforeTime}/afterTime/{afterTime}
13. Get average score of player: http://localhost:8080/top-score-ranking/scores/averageScore/player/{player}
14. Get best score of player: http://localhost:8080/top-score-ranking/scores/bestScore/player/{player}
15. Get lowest score of player: http://localhost:8080/top-score-ranking/scores/lowestScore/player/{player}
