package ru.tinkoff.edu.java.scrapper.util.exceptions;

public class ChatDoesNotExist extends RuntimeException {
    public ChatDoesNotExist(String message) {
        super(message);
    }
}
