package ru.tinkoff.edu.java.scrapper.web;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.request.LinkUpdate;

import java.net.URI;


public class BotClient extends AbstractWebClient {
    private static final String REGULAR_URL = "http://localhost:8080/updates";
    @NotNull
    private final String url;

    public BotClient() {
        this(REGULAR_URL);
    }

    public BotClient(String baseURL) {
        url = baseURL;
    }

    public BotClient newBaseUrl(String BaseUrl){
        return new BotClient(BaseUrl);
    }


    public String getUrl() {
        return url;
    }

    public Void postUpdate(Long id, URI url, String description, Long[] tgChatIds) {
        return webClientWithTimeout()
                .post()
                .uri(this.getUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new LinkUpdate(id, url, description, tgChatIds)), LinkUpdate.class)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();

    }
}
