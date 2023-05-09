package ru.tinkoff.edu.java.scrapper.configuration.accesses;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.LinkRepository;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaChatService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkService;
import ru.tinkoff.edu.java.scrapper.service.jpa.JpaLinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.senders.SenderOfTheLinkUpdate;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public class jpaAccessConfiguration {
    @Bean
    JpaChatService chatService(ChatRepository chatRepository) {
        return new JpaChatService(chatRepository);
    }

    @Bean
    JpaLinkService linkService(ChatLinksRepository chatLinksRepository,
                            LinkRepository linkRepository,
                            ChatRepository chatRepository,
                            URLParser parser) {
        return new JpaLinkService(chatLinksRepository, linkRepository, chatRepository, parser);
    }

    @Bean
    JpaLinkUpdateService linkUpdateService(LinkRepository linkRepository,
                                        ChatLinksRepository chatLinksRepository,
                                        SenderOfTheLinkUpdate senderOfTheLinkUpdate,
                                        URLParser parser,
                                        GitHubClient gitHubClient,
                                        StackOverflowClient stackOverflowClient
    ) {
        return new JpaLinkUpdateService(linkRepository,
                chatLinksRepository, senderOfTheLinkUpdate, parser, gitHubClient, stackOverflowClient);
    }

}
