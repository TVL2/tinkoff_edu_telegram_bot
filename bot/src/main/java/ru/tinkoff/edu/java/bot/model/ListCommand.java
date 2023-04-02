package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;

import java.util.List;

@Component
public class ListCommand implements Command {
    private final ScrapperClient scrapperClient;

    public ListCommand(ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
    }

    @Override
    public String command() {
        return "/list";
    }

    @Override
    public String description() {
        return "получить список ссылок";
    }

    public SendMessage messageToTheUser(Long id) {
        StringBuilder stringBuilder = new StringBuilder();
        ListLinksResponse response = scrapperClient.getLinks(id);
        if (response == null) {
            return new SendMessage(id, "Добавьте чат, команда /start!");
        }
        if (response.size() != 0) {
            List<LinkResponse> links = response.links();
            for (LinkResponse i : links) {
                stringBuilder.append(i.url());
                stringBuilder.append("\n");
            }
            return new SendMessage(id, stringBuilder.toString());
        }
        return new SendMessage(id, "Ссылки не найдены");
    }

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
