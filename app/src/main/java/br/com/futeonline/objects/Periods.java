package br.com.futeonline.objects;

public class Periods {

    private Long id;
    private Languages languages;
    private String name;
    private int numberDays;
    private int numberMonths;

    public Periods() {
        this.id = null;
        this.languages = new Languages();
        this.name = "";
        this.numberDays = 0;
        this.numberMonths = 0;
    }

    public Periods(Long id, Languages languages, String name, int numberDays, int numberMonths) {
        this.id = id;
        this.languages = languages;
        this.name = name;
        this.numberDays = numberDays;
        this.numberMonths = numberMonths;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }


}