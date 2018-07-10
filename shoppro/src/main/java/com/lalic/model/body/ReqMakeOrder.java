package com.lalic.model.body;

import java.util.List;

public class ReqMakeOrder {

    private String userid;

    private String buyOrRent;

    private String addressid;

    private String comment;

    private List<OrderDetail> detail;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddressid() {
        return addressid;
    }

    public void setAddressid(String addressid) {
        this.addressid = addressid;
    }

    public String getBuyOrRent() {
        return buyOrRent;
    }

    public void setBuyOrRent(String buyOrRent) {
        this.buyOrRent = buyOrRent;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<OrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<OrderDetail> detail) {
        this.detail = detail;
    }

    public static class OrderDetail {

        private String productid;

        private String count;

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
