package com.example.stardewweb.services;

import com.example.stardewweb.models.items.fish.Fish;
import com.example.stardewweb.parsers.fish.FishCaughtParser;
import com.example.stardewweb.parsers.fish.FishParser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class FishService {

    private List<Fish> fishes;

    public List<Fish> initFish(){
        FishParser parser = new FishParser();
        fishes = parser.parseJsonFile();
        return fishes;
    }

    public List<Fish> getOrInitFish(){
        if(fishes!=null){
            return fishes;
        }
        else return initFish();
    }

    public List<Fish> FishStatus(HttpSession session) throws Exception {
        FishCaughtParser parser = new FishCaughtParser();
        List<Fish> fishes2 = parser.parseXml(fishes,session);
        return fishes2;
    }
    public Fish findById(int id) {
        return fishes.stream()
                .filter(fish -> fish.getItemID() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Fish> getFishes() {
        return fishes;
    }
}
