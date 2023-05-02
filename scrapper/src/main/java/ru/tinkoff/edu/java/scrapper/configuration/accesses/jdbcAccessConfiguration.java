package ru.tinkoff.edu.java.scrapper.configuration.accesses;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.parser.URLParser;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcLinkRepository;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcChatService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkService;
import ru.tinkoff.edu.java.scrapper.service.jdbc.JdbcLinkUpdateService;
import ru.tinkoff.edu.java.scrapper.service.senders.SenderOfTheLinkUpdate;
import ru.tinkoff.edu.java.scrapper.web.GitHubClient;
import ru.tinkoff.edu.java.scrapper.web.StackOverflowClient;

@Configuration
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jdbc")
public class jdbcAccessConfiguration {


    @Bean
    JdbcChatService chatService(JdbcChatRepository chatRepository) {
        return new JdbcChatService(chatRepository);
    }

    @Bean
    JdbcLinkService linkService(JdbcChatLinksRepository chatLinksRepository,
                            JdbcLinkRepository linkRepository,
                            JdbcChatRepository chatRepository,
                            URLParser parser) {
        return new JdbcLinkService(chatLinksRepository, linkRepository, chatRepository, parser);
    }

    @Bean
    JdbcLinkUpdateService linkUpdateService(JdbcLinkRepository linkRepository,
                                        JdbcChatLinksRepository chatLinksRepository,
                                        SenderOfTheLinkUpdate senderOfTheLinkUpdate,
                                        URLParser parser,
                                        GitHubClient gitHubClient,
                                        StackOverflowClient stackOverflowClient
    ) {
        return new JdbcLinkUpdateService(linkRepository,
                chatLinksRepository, senderOfTheLinkUpdate, parser, gitHubClient, stackOverflowClient);
    }

}
