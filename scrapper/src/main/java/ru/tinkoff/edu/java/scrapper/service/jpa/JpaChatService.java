package ru.tinkoff.edu.java.scrapper.service.jpa;


import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

import java.util.List;


@AllArgsConstructor
@Transactional
public class JpaChatService implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll().stream().map(chat -> new Chat(chat.getId())).toList();
    }

    @Override
    public void save(Long id) {
        chatRepository.save(new JpaChat(id));
    }

    @Override
    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }

}
