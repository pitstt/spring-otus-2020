package ru.otus.hw03springboot;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AppProperty.class)
public class AppConfiguration {

    private final AppProperty appProperty;

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

    @Bean
    public Reader reader() throws IOException {
        return new InputStreamReader(appProperty.getResource().getInputStream());
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public CSVFormat csvFormat() {
        return CSVFormat.DEFAULT
                .withHeader("text", "answer")
                .withDelimiter(';')
                .withSkipHeaderRecord();
    }
}