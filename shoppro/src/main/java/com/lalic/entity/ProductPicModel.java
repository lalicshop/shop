package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_jingxuan")
@Table(name = "shop_jingxuan")
public class ProductPicModel {

    @Id
    @Column(name = "picid")
    private String picid;

    @Column(name = "picid")
    private String productid;

    @Column(name = "sort")
    private int sort;

    @Column(name = "picurl")
    private String picurl;


    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
