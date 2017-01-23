package br.com.ilines.futeonline.objects;

import java.util.Date;

/**
 * Created by rtools2 on 16/01/2017.
 */

public class Services {
    private Long id;

    private Languages languages;

    private ServicesType servicesType;

    private String name;

    private float price;

    private Date recordDate;

    private Date updateDate;
    private boolean active;

    public Services() {
        this.id = null;
        this.languages = new Languages();
        this.servicesType = new ServicesType();
        this.name = "";
        this.price = 0;
        this.recordDate = null;
        this.updateDate = null;
        this.active = false;
    }

    public Services(Long id, Languages languages, ServicesType servicesType, String name, float price, Date recordDate, Date updateDate, boolean active) {
        this.id = id;
        this.languages = languages;
        this.servicesType = servicesType;
        this.name = name;
        this.price = price;
        this.recordDate = recordDate;
        this.updateDate = updateDate;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public ServicesType getServicesType() {
        return servicesType;
    }

    public void setServicesType(ServicesType servicesType) {
        this.servicesType = servicesType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
