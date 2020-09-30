package ru.otus.springshell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

   private final String name;

    private final String surName;

    @Override
    public String toString() {
        return name + " " + surName;
    }
}
