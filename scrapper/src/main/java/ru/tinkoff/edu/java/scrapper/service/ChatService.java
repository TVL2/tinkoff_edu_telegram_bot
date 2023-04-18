package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.entity.Chat;

import java.util.List;

public interface ChatService {

    List<Chat> getAll();

    void save(Long id);

    void deleteById(Long id);

}
