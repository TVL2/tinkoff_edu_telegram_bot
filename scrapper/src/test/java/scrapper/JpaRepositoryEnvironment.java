package scrapper;


import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = JpaRepositoryEnvironment.JpaRepositoryTestConfiguration.class)
@DataJpaTest
public class JpaRepositoryEnvironment extends IntegrationEnvironment {
    @ComponentScan("ru.tinkoff.edu.java.scrapper.repositories.jpa")
    @Configuration
    static class JpaRepositoryTestConfiguration {}
}
