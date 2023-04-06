package ru.tinkoff.edu.java.bot.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.service.NewBot;

@Component
public class BotConfiguration {

    @Autowired
    NewBot newBot;

    @PostConstruct
    private void newBotRun() {
        newBot.run();
    }
}
