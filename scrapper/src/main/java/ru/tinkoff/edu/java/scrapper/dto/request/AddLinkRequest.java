package ru.tinkoff.edu.java.scrapper.dto.request;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

public class AddLinkRequest {

    @NotNull
    private URI link;

    public AddLinkRequest() {
    }

    public AddLinkRequest(URI link) {
        this.link = link;
    }

    public URI getLink() {
        return link;
    }
}

