package ru.tinkoff.edu.java.scrapper.repositories.jooq;

import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Link;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static ru.tinkoff.edu.java.scrapper.domain.jooq.tables.Link.LINK;

@Repository
@AllArgsConstructor
public class JooqLinkRepository {

    private final DSLContext dslContext;


    public void addLink(String link) {
        //"INSERT INTO link(url, last_update) VALUES(?, ?) ON CONFLICT (url) DO NOTHING"
        dslContext.insertInto(LINK, LINK.URL,LINK.LAST_UPDATE).values(link, LocalDateTime.MIN).onConflict(LINK.URL).doNothing();
    }

    public void removeLink(Long id) {
        //"DELETE FROM link WHERE id = ?"
        dslContext.deleteFrom(LINK).where(LINK.ID.eq(id));
    }

    public Link getLink(String url) {
        //"SELECT id, url, last_update FROM link WHERE url = ?"
        return dslContext.select().from(LINK).where(LINK.URL.eq(url)).fetchInto(Link.class).get(0);
    }

    public List<Link> findAll() {
        //"SELECT id, url, last_update FROM link"
        return dslContext.select().from(LINK).fetchInto(Link.class);
    }

    public List<Link> findAllForUpdate(Timestamp timestamp) {
        //"SELECT id, url, last_update FROM link WHERE last_update < ?"
        return dslContext.select().from(LINK).where(LINK.LAST_UPDATE.eq((Field<LocalDateTime>) timestamp)).fetchInto(Link.class);
    }

    public void updateLinkUpdateTime(Timestamp newTime, Long id) {
        //"UPDATE link SET last_update = ? WHERE id = ?"
        dslContext.update(LINK).set(LINK.LAST_UPDATE, (Field<LocalDateTime>)newTime).where(LINK.ID.eq(id));

    }

    public Boolean checkForALink(String link) {
        //"SELECT EXISTS (SELECT * FROM link WHERE url = ?)"
        return dslContext.fetchExists(dslContext.select().from(LINK).where(LINK.URL.eq(link)));
    }
}
