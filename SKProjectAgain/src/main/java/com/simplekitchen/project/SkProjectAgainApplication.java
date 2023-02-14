package com.simplekitchen.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SkProjectAgainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkProjectAgainApplication.class, args);
    }

}
