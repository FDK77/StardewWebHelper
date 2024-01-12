package com.example.stardewweb.parsers.Achivements;

import com.example.stardewweb.models.gameOther.Achivements;
import com.example.stardewweb.models.items.artifacts.Artifacts;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.List;

public class AchievementStatusParser {
    public List<Achivements> parseArtifactsStatus(HttpSession session, List<Achivements> achievementsList) {

        try {
            // Получаем путь к файлу из атрибута сессии "filePath"
            String filePath = (String) session.getAttribute("filePath");
            // Создаем новый DocumentBuilderFactory и DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Парсим XML-файл
            Document doc = builder.parse(new File(filePath));

            NodeList nodeList = doc.getElementsByTagName("achievements");
            if (nodeList.getLength() > 0) {
                Element achievementsElement = (Element) nodeList.item(0);
                NodeList intNodeList = achievementsElement.getElementsByTagName("int");

                for (int i = 0; i < intNodeList.getLength(); i++) {
                    Element intElement = (Element) intNodeList.item(i);
                    int itemId = Integer.parseInt(intElement.getTextContent());

                    for (Achivements ach : achievementsList) {
                        if (ach.getId() == itemId) {
                            ach.setAssembled(true);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Обработайте исключение в соответствии с вашими потребностями
        }
        return achievementsList;
    }
}