package ru.tinkoff.edu.java.bot.model;

import org.springframework.stereotype.Component;

@Component
public class UnKnownCommand {

    public String sendUnKnownCommandMessage() {
        return "Команда неизвестна!";
    }
}
