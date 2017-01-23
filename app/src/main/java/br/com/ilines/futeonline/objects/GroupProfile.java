package br.com.ilines.futeonline.objects;

import java.util.Date;

public class GroupProfile {

    private Long id;
    private UserPlans userPlans;
    private String name;
    private String logo;
    private Date recordDate;
    private boolean active;
    private String url_group_facebook;
    private String url_group_google;
    private AvatarGroupProfile avatarGroupProfile;

    public GroupProfile() {
        this.id = null;
        this.userPlans = new UserPlans();
        this.name = "";
        this.logo = "";
        this.recordDate = new Date();
        this.active = false;
        this.url_group_facebook = "";
        this.url_group_google = "";
        this.avatarGroupProfile = null;
    }

    public GroupProfile(Long id, UserPlans userPlans, String name, String logo, Date recordDate, boolean active, String url_group_facebook, String url_group_google, AvatarGroupProfile avatarGroupProfile) {
        this.id = id;
        this.userPlans = userPlans;
        this.name = name;
        this.logo = logo;
        this.recordDate = recordDate;
        this.active = active;
        this.url_group_facebook = url_group_facebook;
        this.url_group_google = url_group_google;
        this.avatarGroupProfile = avatarGroupProfile;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserPlans getUserPlans() {
        return userPlans;
    }

    public void setUserPlans(UserPlans userPlans) {
        this.userPlans = userPlans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUrl_group_facebook() {
        return url_group_facebook;
    }

    public void setUrl_group_facebook(String url_group_facebook) {
        this.url_group_facebook = url_group_facebook;
    }

    public String getUrl_group_google() {
        return url_group_google;
    }

    public void setUrl_group_google(String url_group_google) {
        this.url_group_google = url_group_google;
    }

    public AvatarGroupProfile getAvatarGroupProfile() {
        return avatarGroupProfile;
    }

    public void setAvatarGroupProfile(AvatarGroupProfile avatarGroupProfile) {
        this.avatarGroupProfile = avatarGroupProfile;
    }


}

