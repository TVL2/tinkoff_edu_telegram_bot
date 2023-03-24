package ru.tinkoff.edu.java.parser;

import ru.tinkoff.edu.java.parser.parsers.LinkParser;
import ru.tinkoff.edu.java.parser.responses.Response;


public class URLParser {

    public Response parse(String link) {
        @SuppressWarnings("unchecked") Class<LinkParser>[] d = (Class<LinkParser>[]) LinkParser.class.getPermittedSubclasses();
        for (Class<LinkParser> linkParserClass : d) {
            Response result = null;
            try {
                result = linkParserClass.getDeclaredConstructor().newInstance().parseLink(link);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
