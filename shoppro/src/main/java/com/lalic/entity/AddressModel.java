package com.lalic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "shop_address")
@Table(name = "shop_address")
public class  AddressModel {

    @Id
    @Column(name = "userid")
    private String userid;

    @Column(name = "province")
    private String province="";

    @Column(name = "city")
    private String city="";

    @Column(name = "district")
    private String district="";

    @Column(name = "detail")
    private String detail="";

    @Column(name = "username")
    private String username="";

    @Column(name = "phone")
    private String phone="";

    @Column(name = "cm")
    private String cm="";

    @Column(name = "kg")
    private String kg="";

    @Column(name = "manager")
    private String manager="";

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
