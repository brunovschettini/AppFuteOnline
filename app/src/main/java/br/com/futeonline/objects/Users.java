package br.com.futeonline.objects;

public class Users {


    private Long id;
    private People people;
    private String email;
    private String password;
    private boolean active;
    private String confirmCode;
    private String accessToken;
    private String photo;
    private static Users INSTANCE;

    public Users() {
        this.id = null;
        this.people = new People();
        this.email = "";
        this.password = "";
        this.active = false;
        this.confirmCode = "";
        this.accessToken = null;
        this.photo = null;
    }

    public Users(Long id, People people, String email, String password, boolean active, String confirmCode, String accessToken) {
        this.id = id;
        this.people = people;
        this.email = email;
        this.password = password;
        this.active = active;
        this.confirmCode = confirmCode;
        this.accessToken = accessToken;
        this.photo = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public static Users getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(Users INSTANCE) {
        Users.INSTANCE = INSTANCE;
    }

}
