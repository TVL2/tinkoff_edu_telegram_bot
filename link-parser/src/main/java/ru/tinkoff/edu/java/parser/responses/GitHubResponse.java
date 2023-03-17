package ru.tinkoff.edu.java.parser.responses;

public record GitHubResponse(String userName, String repository) implements Response{
}
