package ru.tinkoff.edu.java.bot.scrapper_client;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import ru.tinkoff.edu.java.bot.dto.request.AddLink;
import ru.tinkoff.edu.java.bot.dto.request.RemoveLink;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;

import java.net.URI;
import java.util.concurrent.TimeUnit;



public class ScrapperClient {

    private static final String REGULAR_URL = "http://localhost:8081";
    private static final String HEADER_NAME = "Tg-Chat-Id";
    @NotNull
    private final String url;

    public ScrapperClient() {
        this(REGULAR_URL);
    }

    public ScrapperClient(String baseURL) {
        url = baseURL;
    }

    public ScrapperClient newBaseUrl(String BaseUrl){
        return new ScrapperClient(BaseUrl);
    }

    public static final int TIMEOUT = 2000;

    public WebClient webClientWithTimeout() {
        final var httpClient = HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                }).compress(true);


        return WebClient
                .builder()
                .baseUrl(this.getUrl())
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }


    public String getUrl() {
        return url;
    }


    public Void addTelegramChat(Long id) {
        return webClientWithTimeout()
                .post()
                .uri(uriBuilder -> uriBuilder.path("/tg-chat/" + id).build())
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();

    }
    public Void deleteTelegramChat(Long id) {
        return webClientWithTimeout()
                .delete()
                .uri(uriBuilder -> uriBuilder.path("/tg-chat/" + id).build())
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();
    }


    public LinkResponse addLink(Long id, URI link) {
        return webClientWithTimeout()
                .post()
                .uri(uriBuilder -> uriBuilder.path("/links").build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new AddLink(link)), AddLink.class)
                .header(HEADER_NAME, id.toString())
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();
    }

    public LinkResponse removeLink(Long id, URI link) {

        return webClientWithTimeout()
                .method(HttpMethod.DELETE)
                .uri(uriBuilder -> uriBuilder.path("/links").build())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new RemoveLink(link)), AddLink.class)
                .header(HEADER_NAME, id.toString())
                .retrieve()
                .bodyToMono(LinkResponse.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();


    }

    public ListLinksResponse getLinks(Long id) {
        return webClientWithTimeout()
                .get()
                .uri(uriBuilder -> uriBuilder.path("/links").build())
                .accept(MediaType.APPLICATION_JSON)
                .header(HEADER_NAME, id.toString())
                .retrieve()
                .bodyToMono(ListLinksResponse.class)
                .onErrorResume(WebClientResponseException.class,
                        ex -> Mono.empty())
                .block();
    }

}