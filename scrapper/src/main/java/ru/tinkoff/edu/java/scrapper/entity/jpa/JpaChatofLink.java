package ru.tinkoff.edu.java.scrapper.entity.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "chat_links")
public class JpaChatofLink {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat")
    private Long chat;
    @Column(name = "link_id")
    private Long linkId;


    public JpaChatofLink() {
    }

    public JpaChatofLink(Long chat, Long linkId) {
        this.chat = chat;
        this.linkId = linkId;
    }


    public JpaChatofLink(Long chat) {
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChat() {
        return chat;
    }

    public void setChat(Long chat) {
        this.chat = chat;
    }

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

}


