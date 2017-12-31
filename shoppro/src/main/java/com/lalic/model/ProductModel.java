package com.lalic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "product")
@Table(name = "shop_product")
public class ProductModel {

    @Id
    @Column(name = "productid")
    private String productid;

    @Column(name = "name")
    private String name;

    @Column(name = "fleshpart")
    private String fleshpart;

    @Column(name = "inventory")
    private String inventory;

    @Column(name = "buyprice")
    private double buyprice;

    @Column(name = "rentprice")
    private double rentprice;

    @Column(name = "salecount")
    private int salecount;

    @Column(name = "mainpic")
    private String mainpic;

    @Column(name = "attr")
    private String attr;

    @Column(name = "tag")
    private String tag;


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFleshpart() {
        return fleshpart;
    }

    public void setFleshpart(String fleshpart) {
        this.fleshpart = fleshpart;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public double getRentprice() {
        return rentprice;
    }

    public void setRentprice(double rentprice) {
        this.rentprice = rentprice;
    }

    public int getSalecount() {
        return salecount;
    }

    public void setSalecount(int salecount) {
        this.salecount = salecount;
    }

    public String getMainpic() {
        return mainpic;
    }

    public void setMainpic(String mainpic) {
        this.mainpic = mainpic;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
