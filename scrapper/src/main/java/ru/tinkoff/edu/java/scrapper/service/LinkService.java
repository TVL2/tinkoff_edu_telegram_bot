package ru.tinkoff.edu.java.scrapper.service;

import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.entity.Link;

import java.util.List;

public interface LinkService {

    ListLinksResponse getLinksByChatId(Long id);

    LinkResponse saveLink(Long id, AddLinkRequest addLinkRequest);

    LinkResponse deleteLink(Long id, RemoveLinkRequest removeLinkRequest);
}
