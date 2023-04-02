package ru.tinkoff.edu.java.bot.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.model.*;

@Component
public class Commands {

    String status = "x";
    HelpCommand helpCommand;
    ListCommand listCommand;
    StartCommand startCommand;
    TrackCommand trackCommand;
    UnTrackCommand unTrackCommand;
    UnKnownCommand unKnownCommand;

    public Commands(HelpCommand helpCommand,
                    ListCommand listCommand,
                    StartCommand startCommand,
                    TrackCommand trackCommand,
                    UnTrackCommand unTrackCommand,
                    UnKnownCommand unKnownCommand
    ) {

        this.helpCommand = helpCommand;
        this.listCommand = listCommand;
        this.startCommand = startCommand;
        this.trackCommand = trackCommand;
        this.unTrackCommand = unTrackCommand;
        this.unKnownCommand = unKnownCommand;
    }

    public String commands(String command, Long id) {
        String respons;
        switch (command) {
            case "/start" -> {
                status = "x";
                respons = startCommand.messageToTheUser(id);
            }
            case "/help" -> {
                status = "x";
                respons = helpCommand.messageToTheUser();
            }
            case "/track" -> {
                status = "/track";
                respons = "Введите ссылку для добавления или другую команду!";
            }
            case "/untrack" -> {
                status = "/untrack";
                respons = "Введите ссылку для удаления или другую команду!";
            }
            case "/list" -> {
                respons = listCommand.messageToTheUser(id);
                status = "x";
            }
            default -> {
                switch (status) {
                    case "/track" -> respons = trackCommand.messageToTheUser(id, command);
                    case "/untrack" -> respons = unTrackCommand.messageToTheUser(id, command);
                    default -> respons = unKnownCommand.sendUnKnownCommandMessage();
                }
            }
        }
        return respons;
    }
}
