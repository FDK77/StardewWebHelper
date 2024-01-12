package com.example.stardewweb.parsers.fish;

import com.example.stardewweb.models.items.Object;
import com.example.stardewweb.models.items.fish.Fish;
import com.example.stardewweb.parsers.object.ObjectParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishParser {
    public List<Fish> parseJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Fish> fishList = new ArrayList<>();
        ObjectParser objectParser = new ObjectParser();
        Map<String, Object> objectMap = objectParser.parseJsonFile();

        try {
            // Используем getResourceAsStream() для получения InputStream из JAR-ресурса
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/jsondata/fish.json");

            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found!");
            } else {
                // Используем ObjectMapper для чтения JSON из InputStream
                Map<String, Fish> map = mapper.readValue(inputStream, new TypeReference<Map<String, Fish>>() {});
                fishList.addAll(map.values());

                for (Fish fish : fishList) {
                    Object object = objectMap.get(String.valueOf(fish.getItemID()));
                    if (object != null) {
                        fish.setId(object.getId());
                        fish.setCategory(object.getCategory());
                        fish.setDescription(object.getDescription());
                        fish.setName(object.getName());
                        fish.setCaught(false);
                    }
                    fish.setImage();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fishList;
    }
}
