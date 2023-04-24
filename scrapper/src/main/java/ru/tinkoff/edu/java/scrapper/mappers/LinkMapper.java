package ru.tinkoff.edu.java.scrapper.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.entity.Link;

import java.net.URI;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

public class LinkMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {
        Link link = new Link();
        link.setId(rs.getLong("id"));
        link.setLink(URI.create(rs.getString("url")));
        link.setLastUpdate(rs.getObject("last_update", Timestamp.class));

        return link;
    }

}
