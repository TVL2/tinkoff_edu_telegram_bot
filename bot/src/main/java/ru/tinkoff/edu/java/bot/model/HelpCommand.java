package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;


@Component
public class HelpCommand implements Command {

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String description() {
        return "помощь";
    }


    public SendMessage messageToTheUser(Long id) {
        return new SendMessage(id, """
                Список команд:
                /start - добавить чат
                /help - помощь
                /track - добавить ссылку
                /untrack - удалить ссылку
                /list - получить список ссылок
                """);
    }

    @Override
    public BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
