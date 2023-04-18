package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdateService;

import java.sql.Timestamp;
import java.util.List;


@Service
@AllArgsConstructor
public class JdbsLinkUpdateService implements LinkUpdateService {

    private final JdbcLinkRepository linkRepository;
    private final URLParser parser;
    private final Long timeLimitMs = 50000L;

    public void updateLinks(){
        List<Link> AllLinks = linkRepository.findAll();
        Timestamp temporaryFacet = new Timestamp(System.currentTimeMillis() - timeLimitMs);
        List<Link> linksForUpdate = AllLinks.stream().filter(
                link -> link.getLastUpdate() == null || link.getLastUpdate().compareTo(temporaryFacet) == -1).toList();

        for (Link link: linksForUpdate) {

        }
    }
}
