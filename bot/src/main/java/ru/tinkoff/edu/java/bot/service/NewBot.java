package ru.tinkoff.edu.java.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.model.*;


@Service
public class NewBot extends TelegramBot {
    String status;
    HelpCommand helpCommand;
    ListCommand listCommand;
    StartCommand startCommand;
    TrackCommand trackCommand;
    UnTrackCommand unTrackCommand;


    public NewBot(String botToken,
                  HelpCommand helpCommand,
                  ListCommand listCommand,
                  StartCommand startCommand,
                  TrackCommand trackCommand,
                  UnTrackCommand unTrackCommand,
                  ChatMenu chatMenu) {

        super(botToken);
        this.helpCommand = helpCommand;
        this.listCommand = listCommand;
        this.startCommand = startCommand;
        this.trackCommand = trackCommand;
        this.unTrackCommand = unTrackCommand;
        this.execute(chatMenu.getChatMenu());
    }


    public void run() {
        this.setUpdatesListener(updates -> {
            updates.forEach(this::process);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

    private void process(Update update) {
        Message message = update.message();
        if (message != null && message.text() != null) {
            String command = message.text().split(" ")[0];
            Long id = message.chat().id();
            commands(command, id);
        }
    }


    private void commands(String command, Long id) {
        switch (command) {
            case "/start" -> {
                this.execute(startCommand.messageToTheUser(id));
                status = null;
            }
            case "/help" -> {
                this.execute(helpCommand.messageToTheUser(id));
                status = null;
            }
            case "/track" -> {
                status = "/track";
                this.execute(new SendMessage(id, "Введите ссылку для добавления или другую команду!"));
            }
            case "/untrack" -> {
                status = "/untrack";
                this.execute(new SendMessage(id, "Введите ссылку для удаления или другую команду!"));
            }
            case "/list" -> {
                this.execute(listCommand.messageToTheUser(id));
                status = null;
            }
            default -> {
                switch (status) {
                    case "/track" -> this.execute(trackCommand.messageToTheUser(id, command));
                    case "/untrack" -> this.execute(unTrackCommand.messageToTheUser(id, command));
                    default -> sendUnknownCommandMessage(id);
                }
            }
        }

    }

    private void sendUnknownCommandMessage(Long id) {
        this.execute(new SendMessage(id, "Команда неизвестна!"));
    }
}