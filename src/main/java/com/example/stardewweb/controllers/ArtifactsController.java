package com.example.stardewweb.controllers;

import com.example.stardewweb.services.ArtifactsService;
import com.example.stardewweb.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/artifacts")
public class ArtifactsController {
    @Autowired
    ArtifactsService artifactService;

    @GetMapping("/all")
    public String showAllFish(Model model,HttpSession session) throws Exception {
        artifactService.initArtifacts();
        model.addAttribute("artifacts", artifactService.ArtifactsStatus(session));
        return "artifacts-all";
    }
}
