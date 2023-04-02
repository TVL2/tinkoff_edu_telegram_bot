package ru.tinkoff.edu.java.bot.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;



@Component
public class Configuration {

    @Bean
    public String botToken(ApplicationConfig app) {
        return app.token();
    }

    @Bean
    public ScrapperClient scrapperClient() {
        return new ScrapperClient();
    }




}
