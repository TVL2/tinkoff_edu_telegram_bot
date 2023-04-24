package ru.tinkoff.edu.java.scrapper.dto.response;

import jakarta.validation.constraints.NotNull;

import java.net.URI;


public class LinkResponse {

    @NotNull
    private final Long id;
    @NotNull
    private final URI url;

    public LinkResponse(Long id, URI url) {
        this.id = id;

        this.url = url;

    }

    public Long getId() {
        return id;
    }

    public URI getUrl() {
        return url;
    }


}