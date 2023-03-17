package ru.tinkoff.edu.java.parser.parsers;

import ru.tinkoff.edu.java.parser.responses.Response;

public sealed interface LinkParser permits GitHubLinkParser, StackOverflowLinkParser {
    Response parseLink(String link);
}
