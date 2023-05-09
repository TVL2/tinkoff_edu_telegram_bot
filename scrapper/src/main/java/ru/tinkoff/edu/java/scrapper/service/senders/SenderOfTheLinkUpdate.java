package ru.tinkoff.edu.java.scrapper.service.senders;

import java.net.URI;

public interface SenderOfTheLinkUpdate {
    void send(Long id, URI url, String description, Long[] tgChatIds);
}
