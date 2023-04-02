package ru.tinkoff.edu.java.scrapper.dto.request;

import jakarta.validation.constraints.NotNull;

import java.net.URI;

public class RemoveLinkRequest {

    @NotNull
    private URI link;

    public RemoveLinkRequest() {
    }

    public RemoveLinkRequest(URI link) {
        this.link = link;
    }

    public URI getLink() {
        return link;
    }
}

