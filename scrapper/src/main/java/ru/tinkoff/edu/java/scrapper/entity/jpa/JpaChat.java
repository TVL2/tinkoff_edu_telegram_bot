package ru.tinkoff.edu.java.scrapper.entity.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat")
public class JpaChat {
    @Id
    @Column(name = "id")
    private Long id;

    public JpaChat() {
    }

    public JpaChat(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
