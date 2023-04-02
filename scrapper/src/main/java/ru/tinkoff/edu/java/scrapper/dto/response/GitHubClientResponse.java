package ru.tinkoff.edu.java.scrapper.dto.response;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class GitHubClientResponse {
    private long id;
    private String name;
    private OffsetDateTime created_at;
    private OffsetDateTime updated_at;

}
