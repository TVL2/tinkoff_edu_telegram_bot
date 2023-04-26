package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.parser.responses.GitHubResponse;
import ru.tinkoff.edu.java.parser.responses.StackOverflowResponse;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChatofLink;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaLink;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkUpdateService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.BadLink;
import ru.tinkoff.edu.java.scrapper.web.BotClient;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;



@AllArgsConstructor
public class JpaLinkUpdateService implements LinkUpdateService {

    private final LinkRepository linkRepository;
    private final ChatLinksRepository chatLinksRepository;
    private final BotClient botClient;
    private final URLParser parser;
    private final GitHubClient gitHubClient;
    private final StackOverflowClient stackOverflowClient;
    private final Long timeLimitMs = 50000L;

    @Override
    @Transactional
    public void updateLinks() {
        Timestamp temporaryFacet = new Timestamp(System.currentTimeMillis() - timeLimitMs);
        List<JpaLink> jpaLinksForUpdate = linkRepository.findAllForUpdate(temporaryFacet);
        List<Link> linksForUpdate = jpaLinksForUpdate.stream().map(link -> new Link(link.getId(), URI.create(link.getLink()), link.getLastUpdate())).toList();
        for (Link link : linksForUpdate) {
            Timestamp newTime = getUpdateTime(link);
            if (link.getLastUpdate().compareTo(newTime) < 0) {
                Long id = link.getId();
                linkRepository.updateLinkUpdateTime(newTime, id);
                botClient.postUpdate(
                        id,
                        link.getLink(),
                        "Обновление",
                        chatLinksRepository.findAllLinkChats(id).stream().map(JpaChatofLink::getChat).toArray(Long[]::new)
                );
            }
        }
    }

    @Override
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
