package com.example.stardewweb;

import com.example.stardewweb.bundlesStatus.Bundle;
import com.example.stardewweb.bundlesStatus.Room;
import com.example.stardewweb.other.FileWatcher;
import com.example.stardewweb.parsers.XMLIBParser;
import com.example.stardewweb.parsers.XMLParser;

import java.util.List;

public class Main {
        public static void main(String[] args) {
            String filePath = "E:\\MyProjects\\StardewAssistant\\StardewHelper-master\\src\\main\\java\\org\\example\\data\\xml\\data2.xml";
            List<Room> rooms = XMLIBParser.parseXML(filePath);
            XMLParser.parseXML(filePath,rooms);
            while(true) {
                FileWatcher fileWatcher = new FileWatcher(filePath);
                if (fileWatcher.startWatching()) {
                    for (Room room : rooms) {
                        room.analyzeProgress();
                    }

                    for (Room room : rooms) {
                        System.out.println("Room: " + room.getName() + " " + room.isComplete());
                        System.out.println("Bundles: ");
                        for (Bundle bundle : room.getBundles()) {
                            System.out.println(bundle.getName() + " " + bundle.getId() + " " + bundle.isComplete());
                        }
                    }
                }
            }
        }

}
