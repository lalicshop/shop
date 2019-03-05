package com.lalic.entity;

public class ReturnModelExt{

    private String productid;

    private String productname;

    private String returnid ;

    private String userid;

    private String orderid;

    private String quantity;

    private String retdate;

    private String reachdate;

    private String isretmoney;

    private String money;

    private String status;

    private String deliverno;

    private String description;

    private String name;

    private String phone;


    public ReturnModelExt(ReturnModel returning) {
        this.returnid = returning.getReturnid();
        this.userid = returning.getUserid();
        this.orderid = returning.getOrderid();
        this.quantity = returning.getQuantity();
        this.retdate = returning.getRetdate();
        this.reachdate = returning.getReachdate();
        this.isretmoney = returning.getIsretmoney();
        this.money = returning.getMoney();
        this.status = returning.getStatus();
        this.deliverno = returning.getDeliverno();
        this.description = returning.getDescription();
        this.name = returning.getName();
        this.phone = returning.getPhone();
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverno() {
        return deliverno;
    }

    public void setDeliverno(String deliverno) {
        this.deliverno = deliverno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
