package com.example.stardewweb.services;

import com.example.stardewweb.bundlesStatus.Bundle;
import com.example.stardewweb.bundlesStatus.Room;
import com.example.stardewweb.other.FileWatcher;
import com.example.stardewweb.parsers.XMLIBParser;
import com.example.stardewweb.parsers.XMLParser;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoomService {
    public List<Room> initRoom(HttpSession session) {
        String filePath = (String) session.getAttribute("filePath");

        if (filePath == null) {
            // Если путь не сохранен, перенаправить пользователя на страницу ввода пути
            return Collections.emptyList();
        }

        List<Room> rooms = XMLIBParser.parseXML(filePath);
        XMLParser.parseXML(filePath, rooms);
        for (Room room : rooms) {
            room.analyzeProgress();
        }
        return rooms;
    }

}

