package br.com.futeonline.objects;


public class FkLot {


    private Long id;

    public FkLot() {
        this.id = null;
    }

    public FkLot(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}