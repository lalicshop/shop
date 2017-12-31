package com.lalic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_jingxaun")
@Table(name = "shop_jingxaun")
public class JingXuanRespData {

    @Id
    @Column(name = "productid")
    private String productid;

    private double buyprice;

    private double rentprice;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
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
}
