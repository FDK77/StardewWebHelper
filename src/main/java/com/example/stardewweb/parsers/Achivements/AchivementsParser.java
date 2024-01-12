package com.example.stardewweb.parsers.Achivements;

import com.example.stardewweb.models.gameOther.Achivements;
import com.example.stardewweb.models.items.Object;
import com.example.stardewweb.models.items.artifacts.Artifacts;
import com.example.stardewweb.models.items.fish.Fish;
import com.example.stardewweb.parsers.artifacts.ArtifactsParser;
import com.example.stardewweb.parsers.object.ObjectParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AchivementsParser {
    public static List<Achivements> parse() {
        List<Achivements> achievementsList = new ArrayList<>();

        try (InputStream inputStream = AchivementsParser.class.getResourceAsStream("/static/jsondata/achievements.json")) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            Iterator<String> fieldNames = rootNode.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode achievementNode = rootNode.get(fieldName);

                Achivements achievement = new Achivements();
                achievement.setId(achievementNode.get("id").asInt());
                achievement.setName(achievementNode.get("name").asText());
                achievement.setDescription(achievementNode.get("description").asText());
                achievement.setAssembled(false);
                achievement.setImage();
                achievementsList.add(achievement);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return achievementsList;
    }

}

