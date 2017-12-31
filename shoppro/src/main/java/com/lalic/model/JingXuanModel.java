package com.lalic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_jingxaun")
@Table(name = "shop_jingxaun")
public class JingXuanModel {

    @Id
    @Column(name = "productid")
    private String productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

}
