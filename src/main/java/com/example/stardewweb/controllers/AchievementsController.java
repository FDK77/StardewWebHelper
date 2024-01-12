package com.example.stardewweb.controllers;

import com.example.stardewweb.services.AchivementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/achievements")
public class AchievementsController {
    @Autowired
    AchivementsService achService;

    @GetMapping("/all")
    public String getAllAchievements(Model model,HttpSession session) throws Exception {
        achService.initAchievements();

        model.addAttribute("Achievements",achService.AchievementStatus(session));
        return "achievements-all";
    }
}
