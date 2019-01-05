package com.lalic.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_deliver")
@Table(name = "shop_deliver")
public class DeliverModel {

    @Id
    @Column(name = "deliverid")
    private String deliverid= UUID.randomUUID().toString();

    @Column(name = "deliverno")
    private String deliverno;

    @Column(name = "company")
    private String company;

    @Column(name = "detime")
    private String detime;

    @Column(name = "origination")
    private String origination;

    @Column(name = "destination")
    private String destination;

    @Column(name = "orderid")
    private String orderid;

    public String getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(String deliverid) {
        this.deliverid = deliverid;
    }

    public String getDeliverno() {
        return deliverno;
    }

    public void setDeliverno(String deliverno) {
        this.deliverno = deliverno;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDetime() {
        return detime;
    }

    public void setDetime(String detime) {
        this.detime = detime;
    }

    public String getOrigination() {
        return origination;
    }

    public void setOrigination(String origination) {
        this.origination = origination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
