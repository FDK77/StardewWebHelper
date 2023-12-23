package com.example.stardewweb.services;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ShutdownService {

    private final ConfigurableApplicationContext context;

    public ShutdownService(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public void shutdown() {
        context.close();
    }
}