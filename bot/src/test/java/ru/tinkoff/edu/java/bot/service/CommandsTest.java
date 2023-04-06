package ru.tinkoff.edu.java.bot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommandsTest {
    @InjectMocks
    Commands commands;
    @Mock
    HelpCommand helpCommand;
    @Mock
    ListCommand listCommand;
    @Mock
    StartCommand startCommand;
    @Mock
    TrackCommand trackCommand;
    @Mock
    UnTrackCommand unTrackCommand;
    @Mock
    UnKnownCommand unKnownCommand;


    @Test
    public void startCommandTest(){
        when(startCommand.messageToTheUser(1L)).thenReturn("start");
        assertEquals(commands.commands("/start", 1L), "start");
    }
    @Test
    public void listCommandTest(){
        when(listCommand.messageToTheUser(1L)).thenReturn("list");
        assertEquals(commands.commands("/list", 1L), "list");
    }

    @Test
    public void helpCommandTest(){
        when(helpCommand.messageToTheUser()).thenReturn("help");
        assertEquals(commands.commands("/help", 1L), "help");
    }

    @Test
    public void trackCommandTest(){

        assertEquals(commands.commands("/track", 1L),
                "Введите ссылку для добавления или другую команду!");
    }


    @Test
    public void unTrackCommandTest(){
        assertEquals(commands.commands("/untrack", 1L),
                "Введите ссылку для удаления или другую команду!");
    }

    @Test
    public void unKnownCommandTest(){
        when(unKnownCommand.sendUnKnownCommandMessage()).thenReturn("Команда неизвестна!");
        assertEquals(commands.commands("any", 1L), "Команда неизвестна!");
    }

}
