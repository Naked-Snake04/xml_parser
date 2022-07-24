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
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    //TODO: Сделать считывание не одного, а нескольких файлов (узнать про маски)
    final static private String file = "data/plants__000.xml";
    //TODO: Узнать как сохранять в базы данных postgresql
    private static final ArrayList<Plant> plants = new ArrayList<>();
    private static final ArrayList<Catalog> catalogs = new ArrayList<>();

    private static final String url = "jdbc:postgresql://localhost/plant";
    private final static String user = "postgres";
    private final static String password = "Naked_Snake04";

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Plant plant = null;
            Catalog catalog = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(file));
            NodeList nodeList = document.getElementsByTagName("PLANT");
            NodeList nodeList1 = document.getElementsByTagName("CATALOG");
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
                            .item(0).getTextContent().replaceAll("[$]", "")));
                    plant.setAvailability(Integer.parseInt(eElement.getElementsByTagName("AVAILABILITY").item(0).getTextContent()));
                plants.add(plant);
                }
            }

            for (int i = 0; i< nodeList1.getLength(); i++){
                Node node = nodeList1.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    catalog = new Catalog();
                    catalog.setId((int) (Math.random() * 10));
                    catalog.setUuid(eElement.getAttribute("uuid"));
                    catalog.setDate(formatter.parse(eElement.getAttribute("date")));
                    catalog.setCompany(eElement.getAttribute("company"));

                    catalogs.add(catalog);
                }
                }
            for (Catalog catalog1 : catalogs) {
                System.out.println(catalog1.getUuid() + " " + catalog1.getDate() + " "
                + catalog1.getCompany());
                for (Plant value : plants) {
                    System.out.println(value.getCommon() + " " + value.getBotanical() + " "
                            + value.getPrice());
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException | ParseException e) {
            throw new RuntimeException(e);
        }
//        try (Connection connection = DriverManager.getConnection(url, user, password)){
//            System.out.println("Database connected!");
//
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from d_cat_catalog");
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getDate(1));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}