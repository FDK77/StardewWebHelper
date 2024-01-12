package com.example.stardewweb.parsers.rooms;
import com.example.stardewweb.bundlesStatus.Bundle;
import com.example.stardewweb.bundlesStatus.Item;
import com.example.stardewweb.bundlesStatus.Room;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLIBParser {
    public static List<Room> parseXML(String filePath) {
        List<Room> rooms = null;
        List<Bundle> bundles = null;

        try {
            // Создаем построитель документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Считываем XML файл
            Document document = builder.parse(new File(filePath));

            // Получаем список всех элементов bundleData
            NodeList bundleDataList = document.getElementsByTagName("bundleData");

            // Создаем список для хранения объектов Room
            rooms = new ArrayList<>();
            bundles = new ArrayList<>();
            Room room = new Room();
            String roomName = new String();
            // Перебираем элементы bundleData
            for (int i = 0; i < bundleDataList.getLength(); i++) {
                Node bundleDataNode = bundleDataList.item(i);
                if (bundleDataNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bundleDataElement = (Element) bundleDataNode;
                    NodeList itemList = bundleDataElement.getElementsByTagName("item");
                    for (int j = 0; j < itemList.getLength(); j++) {
                        Node itemNode = itemList.item(j);
                        if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemElement = (Element) itemNode;
                            if (j==0){
                                roomName = "Pantry";
                            }

                            // Получаем значения из тега key
                            String keyString = itemElement.getElementsByTagName("key").item(0).getTextContent();
                            int bundleId = extractBundleId(keyString);
                            String currentRoomName = extractRoomName(keyString);
                            // Получаем значения из тега value
                            String valueString = itemElement.getElementsByTagName("value").item(0).getTextContent();
                            List<Item> items = extractItems(valueString);
                            Bundle bundle = new Bundle();
                            //Проверка на новый Room
                            if (!roomName.equals(currentRoomName)) {
                                room.setBundles(bundles);
                                rooms.add(room);
                                room = new Room(roomName);
                                bundles = new ArrayList<>();
                                roomName = extractRoomName(keyString);
                            } else {
                                roomName = extractRoomName(keyString);
                                room = new Room(roomName);
                            }
                            bundle = extractBundle(valueString, items, bundleId);
                            bundles.add(bundle);

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rooms;
    }

    private static String extractRoomName(String keyString) {
        String[] parts = keyString.split("/");
        return parts[parts.length-2].trim();
    }

    private static int extractBundleId(String keyString) {
        // Разбиваем строку по слешам и возвращаем последний элемент
        String[] parts = keyString.split("/");
        return Integer.parseInt(parts[parts.length - 1].trim());
    }
    private static Bundle extractBundle(String valueString,List<Item> items, int bundleId) {
        Integer bundleQuantity = null;
        String[] parts = valueString.split("/");
        String bundleName = parts[0].trim();
        if (parts.length>=5) {
            String quantityInfo = parts[4].trim();
            bundleQuantity = Integer.parseInt(quantityInfo);
        }
        else
        {
            bundleQuantity = items.size();
        }
        return (new Bundle(bundleId,bundleName,items,bundleQuantity));
    }

    private static List<Item> extractItems(String valueString) {
        List<Item> items = new ArrayList<>();

        // Разбиваем строку по слешам и берем третий элемент (индекс 2)
        String[] parts = valueString.split("/");
        String itemsInfo = parts[2].trim();

        // Разбиваем информацию о предметах по пробелам
        String[] itemData = itemsInfo.split(" ");

        // Проходим по каждому тройному блоку данных и создаем объекты Item
        for (int i = 0; i < itemData.length; i += 3) {
            try {
                int id = Integer.parseInt(itemData[i]);
                int quantity = Integer.parseInt(itemData[i + 1]);
                int quality = Integer.parseInt(itemData[i + 2]);
                if (id==-1){
                    quantity = 1;
                    quality = 0;
                }
                items.add(new Item(id, quantity,quality));
            } catch (NumberFormatException e) {
                // Обработка ошибок парсинга, если не удалось преобразовать в число
                e.printStackTrace();
            }
        }
        return items;
    }
}
