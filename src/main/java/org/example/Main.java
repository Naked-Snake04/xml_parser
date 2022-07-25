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

/**
 * Главный класс
 */
public class Main {
    final static private String file = "data/plants__000.xml";
    private static final ArrayList<Plant> plants = new ArrayList<>();

    private static final String url = "jdbc:postgresql://localhost/plant";
    private final static String user = "postgres";
    private final static String password = "Naked_Snake04";

    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        Plant plant;
        Catalog catalog = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); //Форматируем дату
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //Используем DOM
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
                    catalog.setId((int) (Math.random() * 100));
                    catalog.setUuid(eElement.getAttribute("uuid"));
                    catalog.setDate(formatter.parse(eElement.getAttribute("date")));
                    catalog.setCompany(eElement.getAttribute("company"));
                }
                }

        } catch (ParserConfigurationException e) {
            System.out.println("Произошла ошибка конфигурации парсера. Проверьте содержимое файла и повторите ещё раз");
        } catch (IOException e) {
            System.out.println("Произошла ошибка чтения файла. Проверьте правильность пути и повторите ещё раз");
        } catch (ParseException e) {
            System.out.println("Произошла ошибка при парсинге. Проверьте содержимое файла и повторите ещё раз");
        } catch (SAXException e) {
            System.out.println("Произошла ошибка SAX. Проверьте содержимое файла и повторите ещё раз");

        }
        System.out.println("Происходит запись каталогов...");
        assert catalog != null;
        main.insertCatalog(catalog);
        System.out.println("Происходит запись растений...");
        main.insertPlants(plants, catalog);
        System.out.println("Запись завершена.");
    }

    /**
     * Вставляем растения
     * @param plants растения
     * @param catalog каталог
     * @throws SQLException Исключение с SQL
     */
    public void insertPlants(ArrayList<Plant> plants, Catalog catalog) throws SQLException {
        String SQL = "INSERT INTO f_cat_plants(common, botanical, zone, light, price, availability, catalog_id)" +
                " VALUES(?,?,?,?,?,?,?)";
        Connection connection = connect();
        try{
            PreparedStatement statement = connection.prepareStatement(SQL);
                for (Plant plant : plants) {
                    statement.setString(1, plant.getCommon());
                    statement.setString(2, plant.getBotanical());
                    statement.setInt(3, plant.getZone());
                    statement.setString(4, plant.getLight());
                    statement.setDouble(5, plant.getPrice());
                    statement.setInt(6, plant.getAvailability());
                    statement.setInt(7, catalog.getId());
                    statement.executeUpdate();
                }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка с SQL-запросом. Проверьте SQL-запрос и повторите ещё раз");
        }
    }

    /**
     * Вставляем каталог
     * @param catalog каталог
     */
    public void insertCatalog(Catalog catalog){
        String SQL = "INSERT INTO d_cat_catalog(id,uuid,delivery_date,company) VALUES(?,?,?,?)";

        try(
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(SQL)) {

                statement.setInt(1,catalog.getId());
                statement.setString(2, catalog.getUuid());
                statement.setTimestamp(3, new Timestamp(catalog.getDate().getTime()));
                statement.setString(4, catalog.getCompany());

                statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Произошла ошибка с SQL-запросом. Проверьте SQL-запрос и повторите ещё раз");
        }
    }

    /**
     * Связь с БД
     * @return Коннект с БД
     * @throws SQLException Исключения с SQL
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}