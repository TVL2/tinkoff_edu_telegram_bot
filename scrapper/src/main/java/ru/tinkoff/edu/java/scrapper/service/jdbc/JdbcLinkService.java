package ru.tinkoff.edu.java.scrapper.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.BadLink;


import java.net.URI;
import java.util.List;

@Repository
@AllArgsConstructor
public class JdbcLinkService implements LinkService {

    private final JdbcChatLinksRepository chatLinksRepository;
    private final JdbcLinkRepository linkRepository;
    private final URLParser parser;


    @Override
    @Transactional
    public ListLinksResponse getLinksByChatId(Long id) {
        List<Link> links = chatLinksRepository.findAllChatLinks(id);
        return new ListLinksResponse(
                links.stream().map(link -> new LinkResponse(link.getId(), link.getLink())).toList()
        );
    }

    @Override
    @Transactional
    public LinkResponse saveLink(Long id, AddLinkRequest addLinkRequest) {
        String linkRequest = addLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) throw new BadLink("Неверная ссылка");
        linkRepository.addLink(linkRequest);
        Link link = linkRepository.getLink(linkRequest);
        chatLinksRepository.addChatLink(id, link.getId());
        return new LinkResponse(link.getId(), link.getLink());
        //return new LinkResponse(33l, URI.create("link.getLink()"));
    }

    @Override
    @Transactional
    public LinkResponse deleteLink(Long id, RemoveLinkRequest removeLinkRequest) {
        String linkRequest = removeLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) throw new BadLink("Неверная ссылка");
        Link link = linkRepository.getLink(linkRequest);
        chatLinksRepository.removeChatLink(id, link.getId());
        return new LinkResponse(link.getId(), link.getLink());
    }
}
