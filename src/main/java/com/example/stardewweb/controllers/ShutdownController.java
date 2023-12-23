package com.example.stardewweb.controllers;

import com.example.stardewweb.services.ShutdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShutdownController {

    private final ShutdownService shutdownService;

    @Autowired
    public ShutdownController(ShutdownService shutdownService) {
        this.shutdownService = shutdownService;
    }

    @GetMapping("/shutdown")
    public void shutdown() {
        shutdownService.shutdown();
    }
}