package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.model.BotCommand;
import org.springframework.stereotype.Component;


@Component
public interface Command {
    String command();

    String description();


    default BotCommand toApiCommand() {
        return new BotCommand(command(), description());
    }
}
