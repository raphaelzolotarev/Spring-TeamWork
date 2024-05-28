package com.example.springteamwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringTeamWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTeamWorkApplication.class, args);
    }

}
