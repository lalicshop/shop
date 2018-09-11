package com.lalic.model.body;

import java.util.ArrayList;
import java.util.List;

public class CartResp {

    private List<Inner> carts;

    public List<Inner> getCarts() {
        return carts;
    }

    public void setCarts(List<Inner> carts) {
        this.carts = carts;
    }

    public void add(Inner item) {
        if (item == null) return;
        if (carts == null)
            carts = new ArrayList<>();
        carts.add(item);
    }

    public static class Inner {
        private String id;

        private String title;

        private String image;

        private String num;

        private String price;

        private int mode;

        private String cartid;

        public String getCartid() {
            return cartid;
        }

        public void setCartid(String cartid) {
            this.cartid = cartid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getMode() {
            return mode;
        }

        public void setMode(int mode) {
            this.mode = mode;
        }
    }

}
