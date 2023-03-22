package ru.tinkoff.edu.java.scrapper.configuration.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@EnableScheduling
public class LinkUpdaterScheduler {


    @Scheduled(fixedDelayString = "#{@schedulerIntervalMs}")
    @Async
    public void update() {
        log.info("Что-то обновилось!!!!!");

    }

}
