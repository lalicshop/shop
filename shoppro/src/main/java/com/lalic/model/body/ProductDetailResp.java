package com.lalic.model.body;

import java.util.List;

public class ProductDetailResp {

    private Goods goods;

    private Attributes attributes;

    private List<String> subImgUrls;

    private List<String> detailImgUrls;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<String> getSubImgUrls() {
        return subImgUrls;
    }

    public void setSubImgUrls(List<String> subImgUrls) {
        this.subImgUrls = subImgUrls;
    }

    public List<String> getDetailImgUrls() {
        return detailImgUrls;
    }

    public void setDetailImgUrls(List<String> detailImgUrls) {
        this.detailImgUrls = detailImgUrls;
    }

    public static class Goods{

        private String id;
        private String image;
        private String title;
        private String price;
        private String stock;
        private String detail;
        private String parameter;
        private String service;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getParameter() {
            return parameter;
        }

        public void setParameter(String parameter) {
            this.parameter = parameter;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }
    }

    public static class Attributes{
        private String goods_dsp;
        private String goods_cont;
        private String rent_price;
        private String buy_price;

        public String getGoods_dsp() {
            return goods_dsp;
        }

        public void setGoods_dsp(String goods_dsp) {
            this.goods_dsp = goods_dsp;
        }

        public String getGoods_cont() {
            return goods_cont;
        }

        public void setGoods_cont(String goods_cont) {
            this.goods_cont = goods_cont;
        }

        public String getRent_price() {
            return rent_price;
        }

        public void setRent_price(String rent_price) {
            this.rent_price = rent_price;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }
    }


}
