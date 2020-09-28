package ru.otus.springshell.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    String name;

    String surName;

    @Override
    public String toString() {
        return name + " " + surName;
    }
}
