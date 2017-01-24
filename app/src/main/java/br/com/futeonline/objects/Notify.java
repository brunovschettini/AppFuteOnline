package br.com.futeonline.objects;

import java.util.Date;

public class Notify  {


    private Long id;
    private TypeNotify typeNotify;
    private Users usersSent;
    private Users usersReceive;
    private String description;
    private String link;
    private Date recordDate;
    private Date viewDate;
    private GroupProfile groupProfile;
    private Routines routines;

    public Notify() {
        this.id = null;
        this.typeNotify = new TypeNotify();
        this.usersSent = new Users();
        this.usersReceive = new Users();
        this.description = "";
        this.link = "";
        this.recordDate = new Date();
        this.viewDate = null;
        this.groupProfile = null;
        this.routines = null;
    }

    public Notify(Long id, TypeNotify typeNotify, Users usersSent, Users usersReceive, String description, String link, Date recordDate, Date viewDate, GroupProfile groupProfile, Routines routines) {
        this.id = id;
        this.typeNotify = typeNotify;
        this.usersSent = usersSent;
        this.usersReceive = usersReceive;
        this.description = description;
        this.link = link;
        this.recordDate = recordDate;
        this.viewDate = viewDate;
        this.groupProfile = groupProfile;
        this.routines = routines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeNotify getTypeNotify() {
        return typeNotify;
    }

    public void setTypeNotify(TypeNotify typeNotify) {
        this.typeNotify = typeNotify;
    }

    public Users getUsersSent() {
        return usersSent;
    }

    public void setUsersSent(Users usersSent) {
        this.usersSent = usersSent;
    }

    public Users getUsersReceive() {
        return usersReceive;
    }

    public void setUsersReceive(Users usersReceive) {
        this.usersReceive = usersReceive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }


    public Date getViewDate() {
        return viewDate;
    }


    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    public GroupProfile getGroupProfile() {
        return groupProfile;
    }

    public void setGroupProfile(GroupProfile groupProfile) {
        this.groupProfile = groupProfile;
    }

    public Routines getRoutines() {
        return routines;
    }

    public void setRoutines(Routines routines) {
        this.routines = routines;
    }

    @Override
    public String toString() {
        return (recordDate  != null ? recordDate : "") + " - " + (typeNotify != null ? typeNotify.getName() : "") + " - Enviada por: " + (usersSent!= null ? usersSent.getPeople().getName() : "") + " - " + (description != null ? description : "");
    }
}