package ru.tinkoff.edu.java.scrapper.entity;

import java.net.URI;
import java.sql.Timestamp;

public class Link {
    private Long id;
    private URI link;
    private Timestamp lastUpdate;

    public Link() {
    }

    public Link(Long id, URI link, Timestamp lastUpdate) {
        this.id = id;
        this.link = link;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public URI getLink() {
        return link;
    }

    public void setLink(URI link) {
        this.link = link;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
