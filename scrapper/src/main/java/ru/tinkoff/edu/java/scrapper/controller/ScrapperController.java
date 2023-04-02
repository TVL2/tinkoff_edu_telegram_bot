package ru.tinkoff.edu.java.scrapper.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.dto.request.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.request.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.response.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.response.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.model.TelegramChat;
import ru.tinkoff.edu.java.scrapper.service.TelegramChatsService;

import java.util.HashMap;


@RestController
public class ScrapperController {

    TelegramChatsService telegramChats;

    public ScrapperController(TelegramChatsService telegramChats) {
        this.telegramChats = telegramChats;
    }

    @PostMapping("/tg-chat/{id}")
    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чат зарегистрирован"),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )
    public void addTelegramChat(@PathVariable("id") @Valid Long id) {
        telegramChats.save(id);
    }


    @DeleteMapping("/tg-chat/{id}")
    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Чат успешно удалён"),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Чат не существует",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )
    public void deleteTelegramChat(@PathVariable("id") @Valid Long id) {
        telegramChats.deleteById(id);
    }


    @GetMapping("/links")
    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылки успешно получены",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ListLinksResponse.class)
                    )
            ),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )
    public ListLinksResponse getLinksByTelegramChatId(@RequestHeader("Tg-Chat-Id") @Valid Long id) {
        return new ListLinksResponse(telegramChats.getById(id));
    }


    @PostMapping("/links")
    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылка успешно добавлена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LinkResponse.class)
                    )
            ),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long id, @Valid @RequestBody AddLinkRequest body) {
        LinkResponse link = new LinkResponse(body.getLink());
        telegramChats.getById(id).save(link);
        return link;
    }


    @DeleteMapping("/links")
    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ссылка успешно убрана",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LinkResponse.class)
                    )
            ),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Ссылка не найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long id, @Valid @RequestBody RemoveLinkRequest body) {
        System.out.println(body.getLink());
        LinkResponse link = new LinkResponse(body.getLink());
        return telegramChats.getById(id).deleteByURL(link.getUrl());


    }
    @GetMapping("/tg-chat")
    public HashMap<Long, TelegramChat> getTelegramChats() {
        return telegramChats.getAll();
    }


}
