package br.com.futeonline.objects;

import java.util.Date;


public class People {


    private Long id;
    private String name;
    private String midleName;
    private String lastName;
    private Date birthday;
    private Gender gender;
    private String phone;
    private String mobilePhone;
    private String document;
    private Date recordDate;
    // private Address address;

    public People() {
        this.id = null;
        this.name = "";
        this.midleName = "";
        this.lastName = "";
        this.birthday = null;
        this.gender = null;
        this.phone = "";
        this.mobilePhone = "";
        this.document = "";
        this.recordDate = new Date();
        // this.address = null;
    }

    public People(Long id, String name, String midleName, String lastName, Date birthday, Gender gender, String phone, String mobilePhone, String document, Date recordDate) {
        this.id = id;
        this.name = name;
        this.midleName = midleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.document = document;
        this.recordDate = recordDate;
        // this.address = null;
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

    public String getMidleName() {
        return midleName;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }


    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}