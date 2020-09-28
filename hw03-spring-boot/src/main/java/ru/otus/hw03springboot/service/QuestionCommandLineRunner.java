package ru.otus.hw03springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.startMethodEnable", havingValue = "true")
public class QuestionCommandLineRunner implements CommandLineRunner {

    private final QuestionService questionService;

    @Override
    public void run(String... args) throws Exception {
        questionService.start();
    }
}
