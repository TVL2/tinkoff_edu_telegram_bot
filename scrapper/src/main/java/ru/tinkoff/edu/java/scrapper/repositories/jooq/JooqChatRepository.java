package ru.tinkoff.edu.java.scrapper.repositories.jooq;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Chat;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Chat.CHAT;


@Repository
@RequiredArgsConstructor
public class JooqChatRepository {

    private final DSLContext dslContext;


    public void addChat(Long id) {
        //"INSERT INTO chat(id) VALUES(?) ON CONFLICT (id) DO NOTHING"
        dslContext.insertInto(CHAT, CHAT.ID).values(id).onConflict(CHAT.ID).doNothing();

    }

    public void removeChat(Long id) {
        //"DELETE FROM chat WHERE id = ?"
        dslContext.delete(CHAT).where(CHAT.ID.eq(id));
    }

    public List<Chat> findAll() {
        //"SELECT * FROM chat"
        return dslContext.select().from(CHAT).fetchInto(Chat.class);
    }

    public Boolean checkForAChat(Long id) {
        //"SELECT EXISTS (SELECT * FROM chat WHERE id = ?)"
        return dslContext.fetchExists(dslContext.select().from(CHAT).where(CHAT.ID.eq(id)));
    }
}
