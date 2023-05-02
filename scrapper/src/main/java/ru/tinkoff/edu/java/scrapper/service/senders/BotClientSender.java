package ru.tinkoff.edu.java.scrapper.service.senders;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.web.BotClient;

import java.net.URI;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "false")
public class BotClientSender implements  SenderOfTheLinkUpdate{

    private final BotClient botClient;

    @Override
    public void send(Long id, URI url, String description, Long[] tgChatIds){
        botClient.postUpdate(id, url, description, tgChatIds);
    }
}
