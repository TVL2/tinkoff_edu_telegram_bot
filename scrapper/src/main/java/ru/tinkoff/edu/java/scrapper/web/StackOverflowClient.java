package ru.tinkoff.edu.java.scrapper.web;

import jakarta.validation.constraints.NotNull;
import ru.tinkoff.edu.java.scrapper.dto.response.StackOverflowClientResponse;

public class StackOverflowClient extends AbstractWebClient {
    private static final String REGULAR_URL = "https://api.stackexchange.com/2.3/questions/4";
    @NotNull
    private final String url;

    public StackOverflowClient() {
        url = REGULAR_URL;
    }

    public StackOverflowClient(String baseURL) {
        url = baseURL;
    }

    public String getUrl() {
        return url;
    }

    public StackOverflowClientResponse fetchData() {
        StackOverflowData respons = webClientWithTimeout()
                .get()
                .uri(this.getUrl() + "?order=desc&sort=activity&site=stackoverflow")
                .retrieve().bodyToMono(StackOverflowData.class)
                .block();


        return respons.getItems().get(0);
    }
}
