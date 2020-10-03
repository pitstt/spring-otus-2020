package ru.otus.hw05jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Author {

    private final long id;

    private final String name;

    private final String surname;

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
