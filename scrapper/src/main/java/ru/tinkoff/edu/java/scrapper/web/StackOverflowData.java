package ru.tinkoff.edu.java.scrapper.web;


import lombok.Data;
import ru.tinkoff.edu.java.scrapper.dto.response.StackOverflowClientResponse;

import java.util.List;


@Data
public class StackOverflowData {
    List<StackOverflowClientResponse> items;
}


