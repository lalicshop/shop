package com.lalic.entity;

import com.lalic.util.OrderStatusMapping;

public class OrderModelExt {

    private String orderid;

    private String waitpayid;

    private String payorderid;

    private String payby;

    private String userid;

    private int totalprice;

    private String status;

    private String buy_rent;

    private String deliverid;

    private String generatedate;

    private String productid;

    private String quantity;

    private String retquantity;

    private String paydate;

    private String deliverdate;

    private String reachdate;

    private String cm;

    private String kg;

    private String decom;

    private String productname;

    private String phone;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDecom() {
        return decom;
    }

    public void setDecom(String decom) {
        this.decom = decom;
    }

    public OrderModelExt(OrderModel model) {
        this.orderid = model.getOrderid();
        this.waitpayid = model.getWaitpayid();
        this.payorderid = model.getPayorderid();
        this.payby = model.getPayby();
        this.userid = model.getUserid();
        this.totalprice = model.getTotalprice();
        this.buy_rent = model.getBuy_rent();
        this.deliverid = model.getDeliverid();
        this.generatedate = model.getGeneratedate();
        this.productid = model.getProductid();
        this.quantity = model.getQuantity();
        this.retquantity = model.getRetquantity();
        this.paydate = model.getPaydate();
        this.deliverdate = model.getDeliverdate();
        this.reachdate = model.getReachdate();
        this.cm = model.getCm();
        this.kg = model.getKg();
        this.status = OrderStatusMapping.getStatus(model.getStatus());
    }

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
