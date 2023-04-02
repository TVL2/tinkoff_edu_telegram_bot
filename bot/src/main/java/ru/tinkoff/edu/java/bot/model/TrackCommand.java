package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;

import java.net.URI;

@Component
public class TrackCommand implements Command {
    private final ScrapperClient scrapperClient;

    public TrackCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/track";
    }

    @Override
    public String description() {
        return "добавить ссылку";
    }


    public String messageToTheUser(Long id, String link) {
        if (link != null) {
            LinkResponse response = scrapperClient.addLink(id, URI.create(link));
            if (response == null) {
                return "Добавьте чат, команда /start!";
            }
            return "Cсылка " + response.url() + " добавлена!";
        }
        return " Ссылка некорректна!";
    }

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
