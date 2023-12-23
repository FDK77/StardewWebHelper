package com.example.stardewweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class StardewWebApplication {

        public static void main(String[] args) {
            try {
                SpringApplication.run(StardewWebApplication.class, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    @Scheduled(fixedRate = 5000) // Проверка каждые 5 секунд
    public void checkShutdownRequest() {
    }
}
