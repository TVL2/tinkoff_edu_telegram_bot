package ru.tinkoff.edu.java.bot.model;

import com.pengrad.telegrambot.request.SetMyCommands;
import org.springframework.stereotype.Component;


@Component
public class ChatMenu {
    private final SetMyCommands chatMenu;

    public ChatMenu(HelpCommand helpCommand,
                    ListCommand listCommand,
                    StartCommand startCommand,
                    TrackCommand trackCommand,
                    UnTrackCommand unTrackCommand) {
        this.chatMenu = new SetMyCommands(
                helpCommand.toApiCommand(),
                listCommand.toApiCommand(),
                startCommand.toApiCommand(),
                trackCommand.toApiCommand(),
                unTrackCommand.toApiCommand());

    }

    public SetMyCommands getChatMenu() {
        return chatMenu;
    }
}
