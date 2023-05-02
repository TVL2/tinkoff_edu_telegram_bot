package ru.tinkoff.edu.java.scrapper.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.web.BotClient;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;
@Component
public class ClientConfiguration {

    @Bean
    public GitHubClient gitHubClient() {
        return new GitHubClient();
    }

    @Bean
    public StackOverflowClient stackOverflowClient() {
        return new StackOverflowClient();
    }

    @Bean
    public BotClient botClient() {
        return new BotClient();
    }

    @Bean
    URLParser parser(){
        return new URLParser();
    }
}
