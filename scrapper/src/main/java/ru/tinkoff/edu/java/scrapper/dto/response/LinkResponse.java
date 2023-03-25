package ru.tinkoff.edu.java.scrapper.dto.response;

import jakarta.validation.constraints.NotNull;

import java.net.URI;


public class LinkResponse {
    static long counter = 0;
    @NotNull
    private final Long id;
    @NotNull
    private final URI url;

    public LinkResponse(URI url) {
        this.id = counter;

        this.url = url;

        counter++;
    }

    public Long getId() {
        return id;
    }

    public URI getUrl() {
        return url;
    }


}