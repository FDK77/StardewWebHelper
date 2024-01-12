package com.example.stardewweb.parsers.fish;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.*;

import com.example.stardewweb.models.items.fish.Fish;
import org.w3c.dom.*;
import java.io.*;
import java.util.*;

public class FishCaughtParser {
    public static List<Fish> parseXml(List<Fish> fishes, HttpSession session) {
        try {
            // Получаем путь к файлу из атрибута сессии "filePath"
            String filePath = (String) session.getAttribute("filePath");

            // Создаем новый DocumentBuilderFactory и DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Парсим XML-файл
            Document document = builder.parse(new File(filePath));

            // Получаем корневой элемент <player>
            Element playerElement = (Element) document.getElementsByTagName("player").item(0);
            if (playerElement == null){
                System.out.println("Player не найден!");
                return fishes; // Вернуть исходный список, так как нет информации для обработки
            }

            // Находим все элементы <fishCaught> внутри <player>
            NodeList fishCaughtList = playerElement.getElementsByTagName("*");

            // Итерируем по каждому элементу
            for (int i = 0; i < fishCaughtList.getLength(); i++) {
                Node fishCaughtNode = fishCaughtList.item(i);

                if (fishCaughtNode.getNodeType() == Node.ELEMENT_NODE &&
                        "fishCaught".equalsIgnoreCase(fishCaughtNode.getNodeName())) {
                    Element fishCaughtElement = (Element) fishCaughtNode;

                    // Находим все элементы <item> внутри <fishCaught>
                    NodeList itemList = fishCaughtElement.getElementsByTagName("item");

                    // Итерируем по каждому элементу <item>
                    for (int j = 0; j < itemList.getLength(); j++) {
                        Node itemNode = itemList.item(j);

                        if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemElement = (Element) itemNode;

                            // Извлекаем значение элемента <key>
                            int key = Integer.parseInt(itemElement.getElementsByTagName("key")
                                    .item(0).getTextContent());

                            // Устанавливаем caught в true, если key совпадает с ItemID в Fish
                            for (Fish fish : fishes) {
                                if (fish.getItemID() == key) {
                                    fish.setCaught(true);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Обработайте исключение в соответствии с вашими потребностями
        }

        return fishes;
    }
}
