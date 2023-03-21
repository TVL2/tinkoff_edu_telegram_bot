package ru.tinkoff.edu.java.bot.configuration.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.configuration.DTO.response.ApiErrorResponse;
import ru.tinkoff.edu.java.bot.configuration.DTO.request.LinkUpdate;

@RestControllerAdvice
@RestController
public class BotController {


    @PostMapping("/updates")
    @Operation(summary = "Отправить обновление")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обновление обработано"),

            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)
                    )
            )
    }
    )

    public void update(@Valid @RequestBody LinkUpdate body) {

    }


}
