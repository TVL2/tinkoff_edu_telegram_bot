package ru.tinkoff.edu.java.bot.dto.request;


import jakarta.validation.constraints.NotNull;

import java.net.URI;


public class LinkUpdate {

    @NotNull
    private Long id;
    @NotNull
    private URI url;
    @NotNull
    private String description;
    @NotNull
    private Long[] tgChatIds;

    public LinkUpdate() {
    }

    public LinkUpdate(Long id, URI url, String description, Long[] tgChatIds) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.tgChatIds = tgChatIds;
    }

    public Long getId() {
        return id;
    }

    public URI getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Long[] getTgChatIds() {
        return tgChatIds;
    }


}
