package ru.tinkoff.edu.java.bot.dto.response;


import java.util.List;

public class ApiErrorResponse {
    String description;
    String code;
    String exceptionName;
    String exceptionMessage;
    List<String> stacktrace;


    public ApiErrorResponse(String description, String code, String exceptionName, String exceptionMessage, List<String> stacktrace) {
        this.description = description;
        this.code = code;
        this.exceptionName = exceptionName;
        this.exceptionMessage = exceptionMessage;
        this.stacktrace = stacktrace;
    }

    public String getDescription() {
        return description;
    }


    public String getCode() {
        return code;
    }


    public String getExceptionName() {
        return exceptionName;
    }


    public String getExceptionMessage() {
        return exceptionMessage;
    }


    public List<String> getStacktrace() {
        return stacktrace;
    }
}

