package br.com.ilines.futeonline.objects;

import java.util.Date;


public class Plans {


    private Long id;
    private Plans plans;
    private Services services;
    private Languages languages;
    private Periods periods;
    private String name;

    private float price;

    private float discount;

    private Date recordDate;

    private Date validityDate;

    private boolean active;

    private float maxPlayers;

    private float maxGroups;

    public Plans() {
        this.id = null;
        this.plans = null;
        this.services = new Services();
        this.languages = new Languages();
        this.periods = new Periods();
        this.name = "";
        this.price = 0;
        this.discount = 0;
        this.recordDate = new Date();
        this.validityDate = null;
        this.active = false;
        this.maxPlayers = 0;
        this.maxGroups = 0;
    }

    public Plans(Long id, Plans plans, Services services, Languages languages, Periods periods, String name, float price, float discount, Date recordDate, Date validityDate, boolean active, float maxPlayers, float maxGroups) {
        this.id = id;
        this.plans = plans;
        this.services = services;
        this.languages = languages;
        this.periods = periods;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.recordDate = recordDate;
        this.validityDate = validityDate;
        this.active = active;
        this.maxPlayers = maxPlayers;
        this.maxGroups = maxGroups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Plans getPlans() {
        return plans;
    }

    public void setPlans(Plans plans) {
        this.plans = plans;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Languages getLanguages() {
        return languages;
    }

    public void setLanguages(Languages languages) {
        this.languages = languages;
    }

    public Periods getPeriods() {
        return periods;
    }

    public void setPeriods(Periods periods) {
        this.periods = periods;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(Date validityDate) {
        this.validityDate = validityDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(float maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public float getMaxGroups() {
        return maxGroups;
    }

    public void setMaxGroups(float maxGroups) {
        this.maxGroups = maxGroups;
    }
}
