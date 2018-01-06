package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_cart")
@Table(name = "shop_cart")
public class CartModel {

    @Id
    @Column(name = "cartid")
    private String cartid;

    @Column(name = "userid")
    private String userid;

    @Column(name = "productid")
    private String productid;

    @Column(name = "productcount")
    private int productcount;

    @Column(name = "buyrent")
    private String buyrent;

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getProductcount() {
        return productcount;
    }

    public void setProductcount(int productcount) {
        this.productcount = productcount;
    }

    public String getBuyrent() {
        return buyrent;
    }

    public void setBuyrent(String buyrent) {
        this.buyrent = buyrent;
    }
}
