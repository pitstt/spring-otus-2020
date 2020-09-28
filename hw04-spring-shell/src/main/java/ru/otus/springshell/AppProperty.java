package ru.otus.springshell;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.core.io.Resource;

import java.util.Locale;

@Data
@ConstructorBinding
@ConfigurationProperties(prefix = "application")
public class AppProperty {

    private final Resource resource;

    private final Locale locale;
}
