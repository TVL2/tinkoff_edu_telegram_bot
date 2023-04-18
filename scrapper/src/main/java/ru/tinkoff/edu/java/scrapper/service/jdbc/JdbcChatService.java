package ru.tinkoff.edu.java.scrapper.service.jdbc;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.service.ChatService;


import java.util.*;

@Service
@AllArgsConstructor
public class JdbcChatService implements ChatService {

    private final JdbcChatRepository chatRepository;

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
