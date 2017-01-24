package br.com.futeonline.objects;

import java.util.Date;


public class UserPlans {

    private Long id;
    private Plans plans;
    private Users users;
    private FkLot fkLot;
    private Date recordDate;
    private Date acceptDate;
    private Date refusedDate;
    private String reason;

    public UserPlans() {
        this.id = null;
        this.plans = new Plans();
        this.users = new Users();
        this.fkLot = new FkLot();
        this.recordDate = new Date();
        this.acceptDate = null;
        this.refusedDate = null;
        this.reason = "";
    }

    public UserPlans(Long id, Plans plans, Users users, FkLot fkLot, Date recordDate, Date acceptDate, Date refusedDate, String reason) {
        this.id = id;
        this.plans = plans;
        this.users = users;
        this.fkLot = fkLot;
        this.recordDate = recordDate;
        this.acceptDate = acceptDate;
        this.refusedDate = refusedDate;
        this.reason = reason;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public FkLot getFkLot() {
        return fkLot;
    }

    public void setFkLot(FkLot fkLot) {
        this.fkLot = fkLot;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Date getRefusedDate() {
        return refusedDate;
    }

    public void setRefusedDate(Date refusedDate) {
        this.refusedDate = refusedDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
