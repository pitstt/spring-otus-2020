package ru.otus.hw03springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Question {

    private final String text;

    private final String answer;
}
