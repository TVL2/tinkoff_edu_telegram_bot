package ru.tinkoff.edu.java.scrapper.util.exceptions;

public class LinkDoesNotExist extends RuntimeException {
    public LinkDoesNotExist(String message) {
        super(message);
    }
}
