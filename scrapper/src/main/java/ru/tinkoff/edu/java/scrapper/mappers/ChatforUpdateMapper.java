package ru.tinkoff.edu.java.scrapper.mappers;


import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.entity.ChatforUpdate;
import ru.tinkoff.edu.java.scrapper.entity.ChatofLink;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatforUpdateMapper implements RowMapper<ChatforUpdate> {
    @Override
    public ChatforUpdate mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatforUpdate chatforUpdate = new ChatforUpdate();
        chatforUpdate.setChat(rs.getLong("chat"));
        return chatforUpdate;
    }
}
