package ru.tinkoff.edu.java.scrapper.configuration.accesses;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jooq.JooqLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqChatService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkService;
import ru.tinkoff.edu.java.scrapper.service.jooq.JooqLinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.senders.SenderOfTheLinkUpdate;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jooq")
public class jooqAccessConfiguration {
    @Bean
    JooqChatService chatService(JooqChatRepository chatRepository) {
        return new JooqChatService(chatRepository);
    }

    @Bean
    JooqLinkService linkService(JooqChatLinksRepository chatLinksRepository,
                            JooqLinkRepository linkRepository,
                            JooqChatRepository chatRepository,
                            URLParser parser) {
        return new JooqLinkService(chatLinksRepository, linkRepository, chatRepository, parser);
    }

    @Bean
    JooqLinkUpdateService linkUpdateService(JooqLinkRepository linkRepository,
                                        JooqChatLinksRepository chatLinksRepository,
                                        SenderOfTheLinkUpdate senderOfTheLinkUpdate,
                                        URLParser parser,
                                        GitHubClient gitHubClient,
                                        StackOverflowClient stackOverflowClient
    ) {
        return new JooqLinkUpdateService(linkRepository,
                chatLinksRepository, senderOfTheLinkUpdate, parser, gitHubClient, stackOverflowClient);
    }

}
