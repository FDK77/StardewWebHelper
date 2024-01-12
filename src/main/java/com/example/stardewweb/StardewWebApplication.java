package com.example.stardewweb;

import com.example.stardewweb.models.items.artifacts.Artifacts;
import com.example.stardewweb.services.ArtifactsService;
import com.example.stardewweb.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.net.URI;

@SpringBootApplication
@EnableScheduling
public class StardewWebApplication {
    @Autowired
    FishService fishService;

    @Autowired
    ArtifactsService artifactsService;
    public static void main(String[] args) {
        try {
            SpringApplication.run(StardewWebApplication.class, args);
            //Test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedRate = 5000) // Проверка каждые 5 секунд
    public void checkShutdownRequest() {
    }

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI("http://localhost:8081"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8081");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void Test() throws IOException {
        ArtifactsService artifactService = new ArtifactsService();
        List<Artifacts> artifacts = new ArrayList<>();
        artifacts = artifactService.initArtifacts();
        System.out.println(artifacts.size());
        for (Artifacts art:
             artifacts) {
            System.out.println(art);
        }
    }

}
