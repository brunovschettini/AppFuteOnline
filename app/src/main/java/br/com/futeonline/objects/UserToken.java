package br.com.futeonline.objects;

import java.io.Serializable;
import java.util.Date;

public class UserToken implements Serializable {

    private Long id;
    private Users users;
    private Date access;
    private String accessToken;
    private Date expires;

    public UserToken() {
        this.id = null;
        this.users = null;
        this.access = new Date();
        this.accessToken = null;
        this.expires = null;
    }

    public UserToken(Long id, Users users, Date access, String accessToken, Date expires) {
        this.id = id;
        this.users = users;
        this.access = access;
        this.accessToken = accessToken;
        this.expires = expires;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Date getAccess() {
        return access;
    }

    public void setAccess(Date access) {
        this.access = access;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpires() {
        return expires;
    }

    public void setExpires(Date expires) {
        this.expires = expires;
    }

}
