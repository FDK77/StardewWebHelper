package com.example.stardewweb.parsers.artifacts;

import com.example.stardewweb.models.items.artifacts.Artifacts;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArtifactsParser {
    public static List<Artifacts> parseJsonFile() {
        List<Artifacts> artifactsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = ArtifactsParser.class.getResourceAsStream("/static/jsondata/artifacts.json")) {
            // Чтение JSON из файла и преобразование в объект JsonNode
            JsonNode rootNode = objectMapper.readTree(inputStream);

            // Получение узла "artifacts"
            JsonNode artifactsNode = rootNode.get("artifacts");

            // Итерирование по всем артефактам
            Iterator<String> artifactNames = artifactsNode.fieldNames();
            while (artifactNames.hasNext()) {
                String artifactName = artifactNames.next();
                JsonNode artifactNode = artifactsNode.get(artifactName);

                // Парсинг данных артефакта
                int itemID = artifactNode.get("itemID").asInt();
                String itemName = artifactName;
                List<String> locations = parseLocations(artifactNode.get("locations"));

                // Создание объекта Artifacts и добавление в список
                Artifacts artifact = new Artifacts();
                artifact.setItemID(itemID);
                artifact.setName(itemName);
                artifact.setLocations(locations);
                artifact.setImage();

                artifactsList.add(artifact);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return artifactsList;
    }

    private static List<String> parseLocations(JsonNode locationsNode) {
        List<String> locations = new ArrayList<>();
        if (locationsNode.isArray()) {
            for (JsonNode locationNode : locationsNode) {
                locations.add(locationNode.asText());
            }
        }
        return locations;
    }
}
