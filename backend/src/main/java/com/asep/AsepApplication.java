package com.asep;

import com.asep.config.RepositoryProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RepositoryProperties.class)
public class AsepApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsepApplication.class, args);
    }
}
