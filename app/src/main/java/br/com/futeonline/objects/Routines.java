package br.com.futeonline.objects;


public class Routines {


    private Long id;
    private String name;
    private String page;
    private boolean active;
    private boolean privatePage;

    public Routines() {
        this.id = null;
        this.name = "";
        this.page = "";
        this.active = true;
        this.privatePage = false;
    }

    public Routines(Long id, String name, String page, boolean active, boolean privatePage) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.active = active;
        this.privatePage = privatePage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPrivatePage() {
        return privatePage;
    }

    public void setPrivatePage(boolean privatePage) {
        this.privatePage = privatePage;
    }
}
