package com.lalic.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_return")
@Table(name = "shop_return")
public class ReturnModel {

    @Id
    @Column(name = "returnid")
    private String returnid= UUID.randomUUID().toString();

    @Column(name = "userid")
    private String userid;

    @Column(name = "orderid")
    private String orderid;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "retdate")
    private String retdate;

    @Column(name = "reachdate")
    private String reachdate;

    @Column(name = "isretmoney")
    private String isretmoney;

    @Column(name = "money")
    private String money;

    @Column(name = "status")
    private String status;

    @Column(name = "deliverno")
    private String deliverno;

    public String getDeliverno() {
        return deliverno;
    }

    public void setDeliverno(String deliverno) {
        this.deliverno = deliverno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnid() {
        return returnid;
    }

    public void setReturnid(String returnid) {
        this.returnid = returnid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRetdate() {
        return retdate;
    }

    public void setRetdate(String retdate) {
        this.retdate = retdate;
    }

    public String getReachdate() {
        return reachdate;
    }

    public void setReachdate(String reachdate) {
        this.reachdate = reachdate;
    }

    public String getIsretmoney() {
        return isretmoney;
    }

    public void setIsretmoney(String isretmoney) {
        this.isretmoney = isretmoney;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
