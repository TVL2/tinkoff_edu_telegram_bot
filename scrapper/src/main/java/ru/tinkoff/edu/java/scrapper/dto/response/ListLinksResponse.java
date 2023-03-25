package ru.tinkoff.edu.java.scrapper.dto.response;

import ru.tinkoff.edu.java.scrapper.model.TelegramChat;

import java.util.List;

public class ListLinksResponse {
    private final List<LinkResponse> links;
    private final int size;

    public ListLinksResponse(TelegramChat chat) {
        this.links = chat.getAll();
        this.size = links.size();
    }

    public List<LinkResponse> getLinks() {
        return links;
    }

    public int getSize() {
        return size;
    }

}
