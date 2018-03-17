package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_order")
@Table(name = "shop_order")
public class OrderModel {

    @Id
    @Column(name = "orderid")
    private String orderid;

    @Column(name = "payorderid")
    private String payorderid;

    @Column(name = "payby")
    private String payby;

    @Column(name = "userid")
    private String userid;

    @Column(name = "status")
    private String status;

    @Column(name = "totalprice")
    private double totalprice;

    @Column(name = "buy_rent")
    private String buy_rent;

    @Column(name = "comment")
    private String comment;

    @Column(name = "addressid")
    private String addressid;


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getPayorderid() {
        return payorderid;
    }

    public void setPayorderid(String payorderid) {
        this.payorderid = payorderid;
    }

    public String getPayby() {
        return payby;
    }

    public void setPayby(String payby) {
        this.payby = payby;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getBuy_rent() {
        return buy_rent;
    }

    public void setBuy_rent(String buy_rent) {
        this.buy_rent = buy_rent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }
}