package ru.otus.springshell.service;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.springshell.domain.User;

import java.io.IOException;


@ShellComponent
@RequiredArgsConstructor
public class AskQuestionShell {

    private final QuestionService questionService;

    private User user;

    @ShellMethod(key = "user", value = "Введите имя и фамилию:")
    public String createUser(@ShellOption({"userName", "un"}) String name, @ShellOption({"userSurName", "us"})String surName) {
        user = questionService.createUser(name, surName);
        return "Привет " + user.toString();
    }

    @ShellMethod(key = "start", value = "Начать тестирование")
    @ShellMethodAvailability(value = "isPublishEventCommandAvailable")
    public void start() throws IOException {
        questionService.start();
    }

    private Availability isPublishEventCommandAvailable() {
        return user == null? Availability.unavailable("Сначала залогиньтесь"): Availability.available();
    }

}
