package com.simplekitchen.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * класс запуска приложения
 * @since 27.09.2022
 */
@SpringBootApplication
public class SkProjectAgainApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SkProjectAgainApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SkProjectAgainApplication.class);
    }
}
