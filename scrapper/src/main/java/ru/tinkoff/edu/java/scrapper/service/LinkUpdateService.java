package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.entity.Link;

import java.sql.Timestamp;

public interface LinkUpdateService {

    void updateLinks();

    Timestamp getUpdateTime(Link link);

}
