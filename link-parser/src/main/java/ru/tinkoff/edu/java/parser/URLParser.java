package ru.tinkoff.edu.java.parser;

import ru.tinkoff.edu.java.parser.parsers.LinkParser;
import ru.tinkoff.edu.java.parser.responses.Response;

import java.util.ArrayList;
import java.util.List;


public class URLParser {

    private final List<LinkParser> parsers = new ArrayList<>();

    {
        @SuppressWarnings("unchecked") Class<LinkParser>[] d = (Class<LinkParser>[]) LinkParser.class.getPermittedSubclasses();
        for (Class<LinkParser> linkParserClass : d) {
            LinkParser parser;
            try {
                parser = linkParserClass.getDeclaredConstructor().newInstance();
                parsers.add(parser);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    public Response parse(String link) {
        if (link != null) {
            for (LinkParser parser : parsers) {
                Response result = parser.parseLink(link);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
