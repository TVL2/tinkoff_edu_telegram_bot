package ru.tinkoff.edu.java.bot.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.request.LinkUpdate;


@Slf4j
@Service
@RabbitListener(queues = "${app.rabbit-queue}")
@RequiredArgsConstructor
public class ScrapperQueueListener {

    private final NewBot bot;

    @RabbitHandler
    public void receiveUpdate(LinkUpdate update) {

        log.info("Update from RabbitMq: {} !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", update);
        bot.sendALinkUpdateMessage(update);
    }
}
