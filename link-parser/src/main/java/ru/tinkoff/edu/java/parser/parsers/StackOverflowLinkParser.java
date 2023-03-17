package ru.tinkoff.edu.java.parser.parsers;


import ru.tinkoff.edu.java.parser.responses.Response;
import ru.tinkoff.edu.java.parser.responses.StackOverflowResponse;

public record StackOverflowLinkParser() implements LinkParser {
    @Override
    public Response parseLink(String link) {
        if (link.startsWith("https://stackoverflow.com/questions/")) {
            String[] parts = link.split("/");
            if (parts.length == 6) {
                try {
                    Integer result = Integer.parseInt(parts[4]);
                    return new StackOverflowResponse(result);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

}
