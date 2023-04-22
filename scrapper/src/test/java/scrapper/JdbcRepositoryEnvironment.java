package scrapper;


import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;



@ContextConfiguration(classes = JdbcRepositoryEnvironment.JdbcRepositoryTestConfiguration.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcRepositoryEnvironment extends IntegrationEnvironment {
    @ComponentScan("ru.tinkoff.edu.java.scrapper.repositories")
    @Configuration
    static class JdbcRepositoryTestConfiguration {}
}
