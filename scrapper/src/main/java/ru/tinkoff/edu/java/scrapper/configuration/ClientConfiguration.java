package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

public class ClientConfiguration {

    @Bean
    public GitHubClient gitHubClient(GitHubClient gitHubClient) {
        return gitHubClient;
    }

    @Bean
    public StackOverflowClient stackOverflowClient(StackOverflowClient stackOverflowClient) {
        return stackOverflowClient;
    }
}
