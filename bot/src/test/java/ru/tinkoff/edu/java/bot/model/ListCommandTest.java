package ru.tinkoff.edu.java.bot.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.tinkoff.edu.java.bot.dto.response.LinkResponse;
import ru.tinkoff.edu.java.bot.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.bot.scrapper_client.ScrapperClient;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ListCommandTest {


    @InjectMocks
    private ListCommand listCommand;

    @Mock
    ScrapperClient scrapperClient;


    @Test
    public void returnsAMessageWithLinks(){
        LinkResponse link1 = new LinkResponse(1L, URI.create("https://github.com/sanyarnd/tinkoff-java-course-2022/"));
        LinkResponse link2 = new LinkResponse(2L,URI.create("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c"));

        when(scrapperClient.getLinks(1L)).thenReturn(new ListLinksResponse(List.of(link1, link2), 2));
        String result = listCommand.messageToTheUser(1L);
        assertEquals(link1.url() + "\n" + link2.url() + "\n", result);
    }

    @Test
    public void returnsAMessageWithoutLinks(){
        when(scrapperClient.getLinks(1L)).thenReturn(new ListLinksResponse(Collections.emptyList(), 0));
        String result = listCommand.messageToTheUser(1L);
        assertEquals("Ссылки не найдены", result);
    }

    @Test
    public void returnsAMessageAboutTheAbsenceOfAChat(){
        when(scrapperClient.getLinks(1L)).thenReturn(null);
        String result = listCommand.messageToTheUser(1L);
        assertEquals("Добавьте чат, команда /start!", result);
    }






}
