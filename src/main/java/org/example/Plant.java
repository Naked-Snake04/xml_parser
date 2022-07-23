package org.example;

public class Plant {
    private String common;
    private String botanical;
    private int zone;
    private String light;
    private int price;
    private int availability;

    public Plant(String common, String botanical, int zone,
                 String light, int price, int availability){
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

    public int getPrice() {
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
