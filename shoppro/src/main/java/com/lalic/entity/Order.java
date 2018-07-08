package com.lalic.entity;

import java.util.List;

public class Order {

    private String orderid;

    private String payorderid;

    private String payby;

    private String userid;

    private String status;

    private double totalprice;

    private String buy_rent;

    private String comment;

    private String addressid;

    private String deliverid;

    private List<OrderDetailModel> detail;

    public Order(OrderModel order, List<OrderDetailModel> orderDetailByOrderid) {
        this.orderid = order.getOrderid();
        this.payorderid = order.getPayorderid();
        this.payby = order.getPayby();
        this.userid = order.getUserid();
        this.status = order.getStatus();
        this.totalprice = order.getTotalprice();
        this.buy_rent = order.getBuy_rent();
        this.comment = order.getComment();
        this.addressid = order.getAddressid();
        this.detail = orderDetailByOrderid;
    }

    public List<OrderDetailModel> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderDetailModel> detail) {
        this.detail = detail;
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

    public String getDeliverid() {
        return deliverid;
    }

    public void setDeliverid(String deliverid) {
        this.deliverid = deliverid;
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
