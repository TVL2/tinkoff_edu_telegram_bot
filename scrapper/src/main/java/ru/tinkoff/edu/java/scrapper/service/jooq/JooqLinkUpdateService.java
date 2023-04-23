package ru.tinkoff.edu.java.scrapper.service.jooq;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.parser.responses.GitHubResponse;
import ru.tinkoff.edu.java.parser.responses.StackOverflowResponse;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdateService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.BadLink;
import ru.tinkoff.edu.java.scrapper.web.BotClient;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

import java.sql.Timestamp;
import java.util.List;


@Service
@AllArgsConstructor
public class JooqLinkUpdateService implements LinkUpdateService {

    private final JooqLinkRepository linkRepository;
    private final JooqChatLinksRepository jdbcChatLinksRepository;
    private final BotClient botClient;
    private final URLParser parser;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final Long timeLimitMs = 50000L;


    public void updateLinks() {
        Timestamp temporaryFacet = new Timestamp(System.currentTimeMillis() - timeLimitMs);
        List<Link> linksForUpdate = linkRepository.findAllForUpdate(temporaryFacet);
        for (Link link : linksForUpdate) {
            Timestamp newTime = getUpdateTime(link);
            if (link.getLastUpdate().compareTo(newTime) < 0) {
                linkRepository.updateLinkUpdateTime(newTime, link.getId());
                botClient.postUpdate(
                        link.getId(),
                        link.getLink(),
                        "Обновление",
                        jdbcChatLinksRepository.findAllLinkChats(link.getId())
                );
            }
        }
    }

    public Timestamp getUpdateTime(Link link) {
        var result = parser.parse(link.getLink().toString());
        if (result instanceof GitHubResponse) {
            return Timestamp.from(gitHubClient.fetchData().getUpdated_at().toInstant());
        } else if (result instanceof StackOverflowResponse) {
            return Timestamp.from(stackOverflowClient.fetchData().getLast_activity_date().toInstant());
        } else {
            throw new BadLink("Неверная ссылка");
        }
    }
}
