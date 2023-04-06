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

    Commands commands;

    public NewBot(String botToken,
                  Commands commands,
                  ChatMenu chatMenu) {

        super(botToken);
        this.execute(chatMenu.getChatMenu());
        this.commands = commands;
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
            this.execute(new SendMessage(id, commands.commands(command, id)));
        }
    }


}