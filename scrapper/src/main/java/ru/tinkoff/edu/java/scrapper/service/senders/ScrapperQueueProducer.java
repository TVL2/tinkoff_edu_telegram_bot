package ru.tinkoff.edu.java.scrapper.service.senders;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdate;

import java.net.URI;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
public class ScrapperQueueProducer implements SenderOfTheLinkUpdate{

    private final RabbitTemplate template;
    private final Queue queue;

    @Override
    public void send(Long id, URI url, String description, Long[] tgChatIds) {
        LinkUpdate update = new LinkUpdate(id, url, description, tgChatIds);
        template.convertAndSend(queue.getName(), update);
    }
}
