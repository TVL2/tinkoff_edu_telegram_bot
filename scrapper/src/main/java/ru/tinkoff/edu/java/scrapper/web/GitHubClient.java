package ru.tinkoff.edu.java.scrapper.web;

import jakarta.validation.constraints.NotNull;
import ru.tinkoff.edu.java.scrapper.dto.response.GitHubClientResponse;

public class GitHubClient extends AbstractWebClient {
    private static final String REGULAR_URL = "https://api.github.com/repos/TVL2/tinkoff_edu_telegram_bot";
    @NotNull
    private final String url;

    public GitHubClient() {
        url = REGULAR_URL;
    }

    public GitHubClient(String baseURL) {
        url = baseURL;
    }

    public String getUrl() {
        return url;
    }

    public GitHubClientResponse fetchData() {
        return webClientWithTimeout()
                .get()
                .uri(this.getUrl())
                .retrieve().bodyToMono(GitHubClientResponse.class)
                .block();

    }
}
