package ru.tinkoff.edu.java.parser.parsers;

import ru.tinkoff.edu.java.parser.responses.GitHubResponse;
import ru.tinkoff.edu.java.parser.responses.Response;

public record GitHubLinkParser() implements LinkParser {
    @Override
    public Response parseLink(String link) {
        if (link.startsWith("https://github.com/")) {
            String[] parts = link.split("/");
            if (parts.length >= 5) {
                return new GitHubResponse(parts[3], parts[4]);
            }
        }
        return null;
    }
}
