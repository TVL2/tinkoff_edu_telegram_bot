package ru.tinkoff.edu.java.parsers;

public record GitHubLinkParser() implements LinkParser {
    @Override
    public String parseLink(String link) {
        if (link.startsWith("https://github.com/")) {
            String[] parts = link.split("/");
            if (parts.length >= 5) {
                return parts[3] + "/" + parts[4];
            }
        }
        return null;
    }
}
