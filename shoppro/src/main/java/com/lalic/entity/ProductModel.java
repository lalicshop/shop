package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_product")
@Table(name = "shop_product")
public class ProductModel {

    @Id
    @Column(name = "productid")
    private String productid;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "detailname")
    private String detailname;

    @Column(name = "shortdescription")
    private String shortdescription;

    @Column(name = "buyprice")
    private String buyprice;

    @Column(name = "rentprice")
    private String rentprice;

    @Column(name = "size")
    private String size;

    @Column(name = "mess")
    private String mess;

    @Column(name = "attr")
    private String attr;

    @Column(name = "aftersale")
    private String aftersale;

    @Column(name = "mainpic")
    private String mainpic;

    @Column(name = "subpic")
    private String subpic;

    @Column(name = "detailpic")
    private String detailpic;

    @Column(name = "fleshpart")
    private String fleshpart;

    @Column(name = "inventory")
    private String inventory;

    @Column(name = "salecount")
    private String salecount;

    @Column(name = "tag")
    private String tag;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDetailname() {
        return detailname;
    }

    public String getSubpic() {
        return subpic;
    }

    public void setSubpic(String subpic) {
        this.subpic = subpic;
    }

    public void setDetailname(String detailname) {
        this.detailname = detailname;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(String buyprice) {
        this.buyprice = buyprice;
    }

    public String getRentprice() {
        return rentprice;
    }

    public void setRentprice(String rentprice) {
        this.rentprice = rentprice;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getAftersale() {
        return aftersale;
    }

    public void setAftersale(String aftersale) {
        this.aftersale = aftersale;
    }

    public String getMainpic() {
        return mainpic;
    }

    public void setMainpic(String mainpic) {
        this.mainpic = mainpic;
    }

    public String getDetailpic() {
        return detailpic;
    }

    public void setDetailpic(String detailpic) {
        this.detailpic = detailpic;
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

    public String getSalecount() {
        return salecount;
    }

    public void setSalecount(String salecount) {
        this.salecount = salecount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
