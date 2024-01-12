package com.example.stardewweb.services;

import com.example.stardewweb.bundlesStatus.Room;
import com.example.stardewweb.parsers.rooms.XMLIBParser;
import com.example.stardewweb.parsers.rooms.XMLParser;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
public class RoomService {
    public List<Room> initRoom(HttpSession session) {
        String filePath = (String) session.getAttribute("filePath");

        if (filePath == null) {
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

