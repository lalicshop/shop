package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_orderdetail")
@Table(name = "shop_orderdetail")
public class OrderDetailModel {

    @Id
    @Column(name = "orderid")
    private String orderid;

    @Column(name = "productid")
    private String productid;

    @Column(name = "quantity")
    private int quantity;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
