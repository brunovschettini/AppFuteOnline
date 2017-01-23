package br.com.ilines.futeonline.objects;


public class ServicesType {

    private Long id;
    private String name;

    public ServicesType() {
        this.id = null;
        this.name = "";
    }

    public ServicesType(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ServicesType other = (ServicesType) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ServicesType{" + "id=" + id + ", name=" + name + '}';
    }

}
