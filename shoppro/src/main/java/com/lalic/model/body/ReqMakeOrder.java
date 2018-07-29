package com.lalic.model.body;

public class ReqMakeOrder {

    private String userid;

    private String cartId;

    private String buyOrRent;

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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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


}
