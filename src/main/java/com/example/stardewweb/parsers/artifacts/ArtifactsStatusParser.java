package com.example.stardewweb.parsers.artifacts;

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

public class ArtifactsStatusParser {

    public List<Artifacts> parseArtifactsStatus(HttpSession session, List<Artifacts> artifactsList) {

        try  {
            // Получаем путь к файлу из атрибута сессии "filePath"
            String filePath = (String) session.getAttribute("filePath");
            // Создаем новый DocumentBuilderFactory и DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Парсим XML-файл
            Document doc = builder.parse(new File(filePath));
            NodeList nodeList = doc.getElementsByTagName("museumPieces");
            if (nodeList.getLength() > 0) {
                Element museumPiecesElement = (Element) nodeList.item(0);

                NodeList itemNodeList = museumPiecesElement.getElementsByTagName("item");
                for (int i = 0; i < itemNodeList.getLength(); i++) {
                    Node itemNode = itemNodeList.item(i);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) itemNode;

                        int itemId = Integer.parseInt(((Element) itemElement.getElementsByTagName("value").item(0))
                                .getElementsByTagName("int")
                                .item(0)
                                .getTextContent());

                        for (Artifacts artifact : artifactsList) {
                            if (artifact.getItemID() == itemId) {
                                artifact.setAssembled(true);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Обработайте исключение в соответствии с вашими потребностями
        }

        return artifactsList;
    }
}
