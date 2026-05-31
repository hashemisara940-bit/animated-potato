package dev.kairo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.kairo")
public class KairoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KairoApplication.class, args);
    }
}
