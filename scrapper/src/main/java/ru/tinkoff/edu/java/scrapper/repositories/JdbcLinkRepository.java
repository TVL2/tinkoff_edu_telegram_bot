package ru.tinkoff.edu.java.scrapper.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.mappers.LinkMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@AllArgsConstructor
public class JdbcLinkRepository {

    private final JdbcTemplate jdbcTemplate;


    public void addLink(String link) {
        jdbcTemplate.update("INSERT INTO link(url, last_update) VALUES(?, ?) ON CONFLICT (url) DO NOTHING", link, new Timestamp(0));
    }

    public void removeLink(Long id) {
        jdbcTemplate.update("DELETE FROM link WHERE id = ?", id);
    }

    public Link getLink(String url) {
        return jdbcTemplate.queryForStream("SELECT id, url, last_update FROM link WHERE url = ?", new LinkMapper(), url).collect(Collectors.toList()).get(0);
    }

    public List<Link> findAll() {
        return jdbcTemplate.queryForStream("SELECT id, url, last_update FROM link", new LinkMapper()).collect(Collectors.toList());
    }

    public List<Link> findAllForUpdate(Timestamp timestamp) {
        return jdbcTemplate.queryForStream("SELECT id, url, last_update FROM link WHERE last_update < ?", new LinkMapper(), timestamp).collect(Collectors.toList());
    }

    public void updateLinkUpdateTime(Timestamp newTime, Long id) {
        jdbcTemplate.update("UPDATE link SET last_update = ? WHERE id = ?", newTime, id);
    }

    public Boolean checkForALink(String link) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM link WHERE url = ?)", Boolean.class, link);
    }
}
