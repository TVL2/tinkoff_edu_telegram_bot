package ru.tinkoff.edu.java.scrapper.repositories.jooq;


import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.ChatforUpdate;
import ru.tinkoff.edu.java.scrapper.entity.ChatofLink;
import ru.tinkoff.edu.java.scrapper.entity.Link;

import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.ChatLinks.CHAT_LINKS;
import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

@Repository
@RequiredArgsConstructor
public class JooqChatLinksRepository {

    private final DSLContext dslContext;

    public void addChatLink(Long chatId, Long linkId) {
        //"INSERT INTO chat_links(chat, link_id) VALUES(?, ?)"
        dslContext.insertInto(CHAT_LINKS, CHAT_LINKS.CHAT, CHAT_LINKS.LINK_ID).values(chatId, linkId);
    }

    public void removeChatLink(Long chatId, Long linkId) {
        //"DELETE FROM chat_links WHERE chat = ? AND link_id = ?"
        dslContext.delete(CHAT_LINKS).where(CHAT_LINKS.CHAT.eq(chatId).and(CHAT_LINKS.LINK_ID.eq(linkId)));

    }
    public List<ChatofLink> findAll() {
        //"SELECT * FROM chat_links"
        return dslContext.select().from(CHAT_LINKS).fetchInto(ChatofLink.class);
    }


    public List<Link> findAllChatLinks(Long id) {
        //"SELECT id, url, last_update FROM link JOIN chat_links cl on link.id = cl.link_id WHERE cl.chat = ?"
        return dslContext.select(LINK.fields())
                .from(LINK).join(CHAT_LINKS).on(LINK.ID.eq(CHAT_LINKS.LINK_ID))
                .where(CHAT_LINKS.CHAT.eq(id)).fetchInto(Link.class);
    }

    public Long[] findAllLinkChats(Long id) {
        //"SELECT chat FROM chat_links  WHERE link_id = ?"
        return dslContext.select(CHAT_LINKS.CHAT)
                .from(CHAT_LINKS).where(CHAT_LINKS.LINK_ID.eq(id))
                .fetchInto(ChatforUpdate.class)
                .stream().map(ChatforUpdate::getChat).toArray(Long[]::new);
    }

    public Boolean findChatAndLink(Long chatId , Long linkId) {
        //"SELECT EXISTS (SELECT * FROM chat_links WHERE chat = ? AND link_id = ?)"
        return dslContext.fetchExists(dslContext.select()
                .from(CHAT_LINKS).where(CHAT_LINKS.CHAT.eq(chatId)).and(CHAT_LINKS.LINK_ID.eq(linkId)));

    }

}
