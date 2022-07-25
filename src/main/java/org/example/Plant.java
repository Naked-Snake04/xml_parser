package org.example;

import java.util.Objects;

/**
 * Класс растений
 */
public class Plant {
    private String common; //Общее название
    private String botanical; //Ботаническое название
    private int zone; //Зона
    private String light; //Обитание в свете
    private double price; //Цена
    private int availability; //Доступность
    private int catalog_id;

    public Plant(){

    }
    public Plant(String common, String botanical, int zone,
                 String light, double price, int availability){
        this.common = common;
        this.botanical = botanical;
        this.zone = zone;
        this.light = light;
        this.price = price;
        this.availability = availability;
    }

    public int getAvailability() {
        return availability;
    }

    public String getCommon() {
        return common;
    }

    public String getBotanical() {
        return botanical;
    }

    public int getZone() {
        return zone;
    }

    public double getPrice() {
        return price;
    }

    public String getLight() {
        return light;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public void setBotanical(String botanical) {
        this.botanical = botanical;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getCatalog_id(Catalog catalog, String uuid) {
        if (Objects.equals(catalog.getUuid(), uuid))
            catalog_id = catalog.getId();
        return catalog_id;
    }
}
