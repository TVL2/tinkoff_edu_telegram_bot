package ru.tinkoff.edu.java.scrapper.dto.response;


import java.util.List;

public class ListLinksResponse {
    private final List<LinkResponse> links;
    private final int size;

    public ListLinksResponse(List<LinkResponse> links) {
        this.links = links;
        this.size = links.size();
    }

    public List<LinkResponse> getLinks() {
        return links;
    }

    public int getSize() {
        return size;
    }

}
