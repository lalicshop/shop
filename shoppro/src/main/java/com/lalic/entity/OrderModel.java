package com.lalic.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_order")
@Table(name = "shop_order")
public class OrderModel {

    @Id
    @Column(name = "orderid")
    private String orderid = UUID.randomUUID().toString().replace("-","");

    @Column(name = "waitpayid")
    private String waitpayid;

    @Column(name = "payorderid")
    private String payorderid;

    @Column(name = "payby")
    private String payby;

    @Column(name = "userid")
    private String userid;

    @Column(name = "status")
    private String status;

    @Column(name = "totalprice")
    private int totalprice;

    @Column(name = "buy_rent")
    private String buy_rent;

    @Column(name = "deliverid")
    private String deliverid;

    @Column(name = "generatedate")
    private String generatedate;

    @Column(name = "productid")
    private String productid;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "retquantity")
    private String retquantity;

    @Column(name = "paydate")
    private String paydate;

    @Column(name = "deliverdate")
    private String deliverdate;

    @Column(name = "reachdate")
    private String reachdate;

    @Column(name = "cm")
    private String cm;

    @Column(name = "kg")
    private String kg;

    public String getWaitpayid() {
        return waitpayid;
    }

    public void setWaitpayid(String waitpayid) {
        this.waitpayid = waitpayid;
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getGeneratedate() {
        return generatedate;
    }

    public void setGeneratedate(String generatedate) {
        this.generatedate = generatedate;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getDeliverdate() {
        return deliverdate;
    }

    public void setDeliverdate(String deliverdate) {
        this.deliverdate = deliverdate;
    }

    public String getReachdate() {
        return reachdate;
    }

    public void setReachdate(String reachdate) {
        this.reachdate = reachdate;
    }

    public String getRetquantity() {
        return retquantity == null ? "0" : retquantity;
    }

    public void setRetquantity(String retquantity) {
        this.retquantity = retquantity;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getQuantity() {
        return quantity == null ? "0" : quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    public String getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(String deliverid) {
        this.deliverid = deliverid;
    }

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

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getBuy_rent() {
        return buy_rent;
    }

    public void setBuy_rent(String buy_rent) {
        this.buy_rent = buy_rent;
    }

}
