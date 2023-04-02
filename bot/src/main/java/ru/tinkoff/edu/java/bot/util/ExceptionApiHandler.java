package ru.tinkoff.edu.java.bot.util;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.response.ApiErrorResponse;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> badRequest(MethodArgumentNotValidException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiErrorResponse(
                        "Некорректные параметры запроса",
                        exception.getStatusCode().toString(),
                        exception.getClass().getName(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace()).map(String::valueOf).toList()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> badRequest2(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .badRequest()
                .body(new ApiErrorResponse(
                        "Некорректные параметры запроса",
                        "400",
                        exception.getClass().getName(),
                        exception.getMessage(),
                        Arrays.stream(exception.getStackTrace()).map(String::valueOf).toList()));

    }
}
