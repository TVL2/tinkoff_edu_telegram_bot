package ru.tinkoff.edu.java.parsers;

public sealed interface LinkParser permits GitHubLinkParser, StackOverflowLinkParser {
    String parseLink(String link);
}
