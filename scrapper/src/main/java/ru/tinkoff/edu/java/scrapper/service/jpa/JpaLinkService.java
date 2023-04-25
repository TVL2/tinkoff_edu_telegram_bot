package ru.tinkoff.edu.java.scrapper.service.jpa;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChatofLink;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaLink;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.LinkService;
import ru.tinkoff.edu.java.scrapper.util.exceptions.BadLink;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatDoesNotExist;
import ru.tinkoff.edu.java.scrapper.util.exceptions.LinkDoesNotExist;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@Transactional
public class JpaLinkService implements LinkService {


    private final ChatLinksRepository chatLinksRepository;
    private final LinkRepository linkRepository;
    private final ChatRepository chatRepository;
    private final URLParser parser;


    @Override
    public ListLinksResponse getLinksByChatId(Long id) {
        if (!chatRepository.existsById(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        List<JpaChatofLink> chatlinks = chatLinksRepository.findByChat(id);
        List<JpaLink> links = chatlinks.stream().map(chatlink -> linkRepository.findById(chatlink.getLinkId()).get()).toList();
        return new ListLinksResponse(
                links.stream().map(link -> new LinkResponse(link.getId(), URI.create(link.getLink()))).toList()
        );
    }

    @Override
    public LinkResponse saveLink(Long id, AddLinkRequest addLinkRequest) {
        if (!chatRepository.existsById(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        String linkRequest = addLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) {
            throw new BadLink("Неверная ссылка");
        }
        linkRepository.saveLink(linkRequest, new Timestamp(0));
        JpaLink link = linkRepository.findByUrl(linkRequest);
        System.out.println(link.getId());
        if (chatLinksRepository.existsByChatAndLinkId(id, link.getId())) {
            return new LinkResponse(link.getId(), URI.create(link.getLink()));
        }
        chatLinksRepository.save(new JpaChatofLink(id, link.getId()));
        return new LinkResponse(link.getId(), URI.create(link.getLink()));

    }

    @Override
    public LinkResponse deleteLink(Long id, RemoveLinkRequest removeLinkRequest) {
        String linkRequest = removeLinkRequest.getLink().toString();
        if (parser.parse(linkRequest) == null) {
            throw new BadLink("Неверная ссылка");
        }
        if (!chatRepository.existsById(id)) {
            throw new ChatDoesNotExist("Чат не существует");
        }
        if (!linkRepository.existsByUrl(linkRequest)) {
            throw new LinkDoesNotExist("Ссылка не найдена!");
        }
        JpaLink link = linkRepository.findByUrl(linkRequest);
        if (!chatLinksRepository.existsByChatAndLinkId(id, link.getId())) {
            throw new LinkDoesNotExist("Ссылка не найдена (link)!");
        }
        chatLinksRepository.deleteByChatAndLinkId(id, link.getId());

        if(!chatLinksRepository.existsByLinkId(link.getId())){
            linkRepository.deleteById(link.getId());
        }
        return new LinkResponse(link.getId(), URI.create(link.getLink()));
    }
}
