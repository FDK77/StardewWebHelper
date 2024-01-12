package com.example.stardewweb.controllers;

import com.example.stardewweb.models.items.fish.Fish;
import com.example.stardewweb.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/fish")
public class FishController {
    @Autowired
    FishService fishService;
    @GetMapping("/all")
    public String showAllFish(Model model,HttpSession session) throws Exception {
        fishService.initFish();
        model.addAttribute("fishes", fishService.FishStatus(session)) ;
        return "fish-all";
    }

    @GetMapping("/detailed/{id}")
    public String showFishDetails(@PathVariable("id") int id, Model model) {
        Fish fish = fishService.findById(id);
        model.addAttribute("fish", fish);
        return "fish-detailed";
    }

}



