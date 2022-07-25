package org.example;

import java.util.Date;

/**
 * Класс каталог
 */
public class Catalog {
    private String uuid; //uuid каталога
    private Date date; //Дата
    private String company; //Компания
    private int id; //id

    /**
     * Пустой конструктор
     */
    public Catalog(){

    }

    /**
     * Конструктор
     * @param uuid uuid каталога
     * @param date Дата
     * @param company Компания
     */
    public Catalog(String uuid, Date date, String company){
        this.uuid = uuid;
        this.date = date;
        this.company = company;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getDate() {
        return date;
    }

    public String getCompany() {
        return company;
    }


    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
