package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;


@Component
public class StartCommand implements Command {
    private final ScrapperClient scrapperClient;

    public StartCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String description() {
        return "добавить чат";
    }


    public String messageToTheUser(Long id) {
        scrapperClient.addTelegramChat(id);
        return "Чат добавлен!";
    }

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
