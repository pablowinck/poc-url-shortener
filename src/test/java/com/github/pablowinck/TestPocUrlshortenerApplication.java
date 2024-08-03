package com.github.pablowinck;

import org.springframework.boot.SpringApplication;

public class TestPocUrlshortenerApplication {

    public static void main(String[] args) {
        SpringApplication.from(PocUrlshortenerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
