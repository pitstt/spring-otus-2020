package spring;

import org.apache.commons.csv.CSVFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfiguration {

    @Bean
    public Reader reader(@Value("${resource}") Resource resource) throws IOException {
        return new InputStreamReader(resource.getInputStream());
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public CSVFormat csvFormat(){
        return CSVFormat.DEFAULT
                .withHeader("text", "answer")
                .withDelimiter(';')
                .withSkipHeaderRecord();
    }
}