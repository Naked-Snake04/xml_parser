package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    final static private String file = "data/plants__000.xml";


    private static final ArrayList<Plant> plants = new ArrayList<>();
    public static void main(String[] args) {
        try {
            Plant plant = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(file));
            NodeList nodeList = document.getElementsByTagName("PLANT");

            for (int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) node;

                    plant = new Plant();
                    plant.setCommon(eElement.getElementsByTagName("COMMON").item(0).getTextContent());
                    plant.setBotanical(eElement.getElementsByTagName("BOTANICAL").item(0).getTextContent());
                    plant.setZone(Integer.parseInt(eElement.getElementsByTagName("ZONE").item(0).getTextContent()));
                    plant.setLight(eElement.getElementsByTagName("LIGHT").item(0).getTextContent());
                    plant.setPrice(Double.parseDouble(eElement.getElementsByTagName("PRICE")
                            .item(0).getTextContent().replaceAll("[$]*", "")));
                    plant.setAvailability(Integer.parseInt(eElement.getElementsByTagName("AVAILABILITY").item(0).getTextContent()));
                plants.add(plant);
                }
            }
            for (Plant value : plants) {
                System.out.println(value.getCommon() + " " + value.getBotanical() + " "
                + value.getPrice());
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }
}