package ru.tinkoff.edu.java.scrapper.model;


import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.util.exceptions.LinkDoesNotExist;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TelegramChat {

    private final List<LinkResponse> links = new ArrayList<>();


    {

        try {
            links.add(new LinkResponse(new URI("https://github.com/sanyarnd/tinkoff-java-course-2022/")));
            links.add(new LinkResponse(new URI("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c")));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public void save(LinkResponse link) {
        links.add(link);
    }

    public LinkResponse deleteByURL(URI url) {
        Optional<LinkResponse> first = links.stream()
                .filter(p -> p.getUrl().equals(url))
                .findFirst();
        if (first.isEmpty()) {
            throw new LinkDoesNotExist("Ссылка не найдена");
        }
        LinkResponse deleteLink = first.get();
        links.remove(deleteLink);
        return deleteLink;


    }

    public List<LinkResponse> getAll() {
        return links;
    }


}
