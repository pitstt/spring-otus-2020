package ru.otus.hw05jdbc.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Genre {

    private final long id;

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
