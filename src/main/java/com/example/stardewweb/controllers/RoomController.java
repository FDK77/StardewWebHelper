package com.example.stardewweb.controllers;

import com.example.stardewweb.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequestMapping("/")
public class RoomController {
    @Autowired
    RoomService roomService;
    @GetMapping("/rooms")
    public String showAllRooms(Model model, HttpSession session) {
        model.addAttribute("rooms", roomService.initRoom(session));
        return "rooms";
    }
    @PostMapping("/setPath")
    public String setFilePath(@RequestParam String filePath, HttpSession session) {
        String userHome = System.getProperty("user.home");
        String savesDirectory = userHome + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "StardewValley" + File.separator + "Saves";
        String fullPath = savesDirectory + File.separator + filePath;
        session.setAttribute("filePath", fullPath);
        return "redirect:/rooms";
    }

    @GetMapping("/enterPath")
    public String enterPathForm(Model model) {
        String userHome = System.getProperty("user.home");
        String directoryPath = userHome + File.separator + "AppData" + File.separator + "Roaming" + File.separator + "StardewValley" + File.separator + "Saves";
        File directory = new File(directoryPath );
        String[] saveFiles = directory.list();

        model.addAttribute("saveFiles", saveFiles);
        return "enterPath";
    }
}
