package com.micro.i113_telbot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "TelegramBot"))
public class I113TelbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(I113TelbotApplication.class, args);
    }

}
