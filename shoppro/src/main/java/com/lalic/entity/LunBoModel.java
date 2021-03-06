package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_lunbo")
@Table(name = "shop_lunbo")
public class LunBoModel {

    @Id
    @Column(name = "productid")
    private String productid;

    @Column(name = "lunboimgurl")
    private String lunboimgurl;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getLunboimgurl() {
        return lunboimgurl;
    }

    public void setLunboimgurl(String lunboimgurl) {
        this.lunboimgurl = lunboimgurl;
    }
}
