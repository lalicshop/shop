package com.lalic.model.body;

import java.util.List;

public class ReqMakeOrder {

    private String userid;

    private String buyOrRent;

    public String getBuyOrRent() {
        return buyOrRent;
    }

    public void setBuyOrRent(String buyOrRent) {
        this.buyOrRent = buyOrRent;
    }

    private List<OrderDetail> detail;

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
