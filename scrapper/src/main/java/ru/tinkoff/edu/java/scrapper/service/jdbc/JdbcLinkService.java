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
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.BadLink;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatDoesNotExist;
import ru.tinkoff.edu.java.scrapper.util.exceptions.LinkDoesNotExist;

import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
@Transactional
public class JdbcLinkService implements LinkService {


    private final JdbcChatLinksRepository chatLinksRepository;
    private final JdbcLinkRepository linkRepository;
    private final JdbcChatRepository chatRepository;
    private final URLParser parser;


    @Override
    public ListLinksResponse getLinksByChatId(Long id) {
        if (!chatRepository.checkForAChat(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        List<Link> links = chatLinksRepository.findAllChatLinks(id);
        return new ListLinksResponse(
                links.stream().map(link -> new LinkResponse(link.getId(), link.getLink())).toList()
        );
    }

    @Override
    public LinkResponse saveLink(Long id, AddLinkRequest addLinkRequest) {
        if (!chatRepository.checkForAChat(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        String linkRequest = addLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) {
            throw new BadLink("Неверная ссылка");
        }
        linkRepository.addLink(linkRequest);
        Link link = linkRepository.getLink(linkRequest);
        if (chatLinksRepository.findChatAndLink(id, link.getId())) {
            return new LinkResponse(link.getId(), link.getLink());
        }
        chatLinksRepository.addChatLink(id, link.getId());
        return new LinkResponse(link.getId(), link.getLink());
    }

    @Override
    public LinkResponse deleteLink(Long id, RemoveLinkRequest removeLinkRequest) {
        String linkRequest = removeLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) {
            throw new BadLink("Неверная ссылка");
        }
        if (!chatRepository.checkForAChat(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        if (!linkRepository.checkForALink(linkRequest)) {
            throw new LinkDoesNotExist("Ссылка не найдена!");
        }
        Link link = linkRepository.getLink(linkRequest);
        if (!chatLinksRepository.findChatAndLink(id, link.getId())) {
            throw new LinkDoesNotExist("Ссылка не найдена!");
        }
        chatLinksRepository.removeChatLink(id, link.getId());

        if(Arrays.stream(chatLinksRepository.findAllLinkChats(link.getId())).toList().isEmpty()){
            linkRepository.removeLink(link.getId());
        }
        return new LinkResponse(link.getId(), link.getLink());
    }
}
