package ru.tinkoff.edu.java.scrapper.service;


import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatDoesNotExist;
import ru.tinkoff.edu.java.scrapper.model.TelegramChat;

import java.util.*;

@Service
public class TelegramChatsService {

    private final HashMap<Long, TelegramChat> chats = new HashMap<>();


    public HashMap<Long, TelegramChat> getAll() {
        return chats;
    }

    public void save(Long id) {
        if (chats.get(id) == null) {
            chats.put(id, new TelegramChat());
        }
    }

    public void deleteById(Long id) {
        if (!chats.containsKey(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        chats.remove(id);

    }

    public TelegramChat getById(Long id) {
        if (!chats.containsKey(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        return chats.get(id);
    }

}
