package ru.tinkoff.edu.java.scrapper.service.jooq;


import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;

import java.util.List;


@AllArgsConstructor
@Transactional
public class JooqChatService implements ChatService {

    private final JooqChatRepository chatRepository;

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public void save(Long id) {
        chatRepository.addChat(id);
    }

    @Override
    public void deleteById(Long id) {
        chatRepository.removeChat(id);
    }

}
