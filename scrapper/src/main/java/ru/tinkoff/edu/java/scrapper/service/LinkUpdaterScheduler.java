package ru.tinkoff.edu.java.scrapper.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkUpdateService;


@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class LinkUpdaterScheduler {

    private final JdbcLinkUpdateService linkUpdateService;

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    @Async
    public void update() {
        log.info("Поиск обновлений!");
        linkUpdateService.updateLinks();

    }

}
