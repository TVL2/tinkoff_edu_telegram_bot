package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;

import java.net.URI;

@Component
public class UnTrackCommand implements Command {
    private final ScrapperClient scrapperClient;

    public UnTrackCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/untrack";
    }

    @Override
    public String description() {
        return "удалить ссылку";
    }


    public String messageToTheUser(Long id, String link) {
        if (link != null) {
            LinkResponse response = scrapperClient.removeLink(id, URI.create(link));
            if (response == null) {
                return "Ссылка не найдена!";
            }
            return "Cсылка " + response.url().toString() + " удалена!";
        }
        return " Ссылка некорректна!";
    }

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
