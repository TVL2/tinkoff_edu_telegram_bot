package ru.tinkoff.edu.java.scrapper.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@EnableScheduling
@AllArgsConstructor
public class LinkUpdaterScheduler {

    private final LinkUpdateService linkUpdateService;

    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    @Async
    public void update() {
        log.info("Поиск обновлений!");
        linkUpdateService.updateLinks();

    }

}
