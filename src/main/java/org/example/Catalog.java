package org.example;

import java.util.Date;

public class Catalog {
    private String uuid;
    private Date date;
    private String company;

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
}
