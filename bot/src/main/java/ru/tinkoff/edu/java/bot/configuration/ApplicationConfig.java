package ru.tinkoff.edu.java.bot.configuration;


import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test,
                                String token,
                                String rabbitQueue,
                                String rabbitExchange,
                                boolean useQueue
) {


}
