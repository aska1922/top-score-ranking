//package api.topscoreranking;
//
//
//import api.topscoreranking.entities.Score;
//import api.topscoreranking.repos.ScoresRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.TestPropertySource;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//
//import java.util.Date;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//@DataJpaTest
//@SpringBootTest
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=validate"
//})
//public class ScoreEntityRepositoryTest {
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired private JdbcTemplate jdbcTemplate;
//    @Autowired private EntityManager entityManager;
//    @Autowired private ScoresRepository scoresRepository;
//
//    @Test
//    void injectedComponentsAreNotNull(){
////        assertThat(dataSource).isNotNull();
////        assertThat(jdbcTemplate).isNotNull();
////        assertThat(entityManager).isNotNull();
////        assertThat(scoresRepository).isNotNull();
//    }
//
////    @Test
////    public void testFindByPlayer() {
////        Pageable pageable = PageRequest.of(0, 5);
////        Score score = new Score();
////        score.setPlayer("Foobar");
////        score.setScore(4);
////        score.setTime(new Date());
////        entityManager.persist(score);
////        entityManager.flush();
////
////        Page<Score> res = scoresRepository.findByPlayer(score.getPlayer(), pageable);
////        assertThat(res.get().findFirst().get().getPlayer(), is("Foobar"));
////    }
//
//}
