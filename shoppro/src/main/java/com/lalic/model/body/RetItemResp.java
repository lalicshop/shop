package com.lalic.model.body;

import com.lalic.entity.ReturnModel;

public class RetItemResp {
    private String productid;
    private String imgurl;
    private String returnid;
    private String orderid;
    private String quantity;
    private String retdate;
    private String reachdate;
    private String isretmoney;
    private String money;
    private int status;
    private String deliverno;
    private Object deliverDetail;
    private String title;

    public RetItemResp(ReturnModel returnModel,String productid,String imgurl,String productTitle) {
        this.productid=productid;
        this.imgurl=imgurl;
        returnid=returnModel.getReturnid();
        orderid=returnModel.getOrderid();
        quantity=returnModel.getQuantity();
        retdate=returnModel.getRetdate();
        reachdate=returnModel.getReachdate();
        isretmoney=returnModel.getIsretmoney();
        money=returnModel.getMoney();
        status=Integer.valueOf(returnModel.getStatus());
        deliverno=returnModel.getDeliverno();
        title=productTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getReturnid() {
        return returnid;
    }

    public void setReturnid(String returnid) {
        this.returnid = returnid;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDeliverno() {
        return deliverno;
    }

    public void setDeliverno(String deliverno) {
        this.deliverno = deliverno;
    }

    public Object getDeliverDetail() {
        return deliverDetail;
    }

    public void setDeliverDetail(Object deliverDetail) {
        this.deliverDetail = deliverDetail;
    }
}
