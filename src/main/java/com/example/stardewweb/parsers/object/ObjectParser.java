package com.example.stardewweb.parsers.object;

import com.example.stardewweb.models.items.Object;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ObjectParser {
    public Map<String, Object> parseJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> objectMap = new HashMap<>();

        try {
            // Используем getResourceAsStream() для получения InputStream из JAR-ресурса
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/jsondata/objects.json");

            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found!");
            } else {
                // Используем ObjectMapper для чтения JSON из InputStream
                objectMap = mapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectMap;
    }
}
