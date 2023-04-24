package ru.tinkoff.edu.java.scrapper.entity;

public class ChatofLink {
    private Long chat;

    private Long Link_id;

    public ChatofLink() {
    }

    public ChatofLink(Long chat, Long link_id) {
        this.chat = chat;
        Link_id = link_id;
    }

    public ChatofLink(Long chat) {
        this.chat = chat;
    }

    public Long getChat() {
        return chat;
    }

    public void setChat(Long chat) {
        this.chat = chat;
    }

    public Long getLink_id() {
        return Link_id;
    }

    public void setLink_id(Long link_id) {
        Link_id = link_id;
    }
}
