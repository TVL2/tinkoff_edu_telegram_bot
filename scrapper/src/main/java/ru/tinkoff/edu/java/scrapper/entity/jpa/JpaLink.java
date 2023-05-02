package ru.tinkoff.edu.java.scrapper.entity.jpa;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "link")
public class JpaLink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public JpaLink() {
    }

    public JpaLink(String url, Timestamp lastUpdate) {
        this.url = url;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return url;
    }

    public void setLink(String url) {
        this.url = url;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
