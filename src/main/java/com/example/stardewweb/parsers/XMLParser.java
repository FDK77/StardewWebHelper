package com.example.stardewweb.parsers;
import com.example.stardewweb.bundlesStatus.Bundle;
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

public class XMLParser {

    public static void parseXML(String filePath,List<Room> rooms) {

        try {
            // Создаем построитель документа
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Считываем XML файл
            Document document = builder.parse(new File(filePath));

            // Получаем список всех элементов bundles
            NodeList bundlesList = document.getElementsByTagName("bundles");

            Node bundlesNode = bundlesList.item(0);
            if (bundlesNode.getNodeType() == Node.ELEMENT_NODE) {
                Element bundlesElement = (Element) bundlesNode;

                // Получаем список всех элементов item
                NodeList itemList = bundlesElement.getElementsByTagName("item");

                // Перебираем элементы item
                for (int j = 0; j < itemList.getLength(); j++) {
                    Node itemNode = itemList.item(j);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) itemNode;

                        // Получаем значение id
                        int id = extractBundleId(itemElement);

                        // Получаем значения из ArrayOfBoolean
                        List<Boolean> booleanList = extractBooleanList(itemElement);

                        // Создаем объект BundleProgress и добавляем его в список
                        for (Room room : rooms) {
                            List<Bundle> bundles = room.getBundles();
                            for (Bundle bundle : bundles) {
                                if (bundle.getId() == id) {
                                    bundle.setProgressList(booleanList);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static int extractBundleId(Element itemElement) {
        // Получаем значение id
        String idString = itemElement.getElementsByTagName("int").item(0).getTextContent();
        return Integer.parseInt(idString);
    }

    private static List<Boolean> extractBooleanList(Element itemElement) {
        List<Boolean> booleanList = new ArrayList<>();

        // Получаем значения из ArrayOfBoolean
        NodeList booleanNodeList = itemElement.getElementsByTagName("boolean");
        for (int k = 0; k < booleanNodeList.getLength(); k++) {
            boolean booleanValue = Boolean.parseBoolean(booleanNodeList.item(k).getTextContent());
            booleanList.add(booleanValue);
        }

        return booleanList;
    }
}