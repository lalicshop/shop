package com.lalic.model.body;

import java.util.ArrayList;
import java.util.List;

public class AllOrderResp {
    private List<OrderItem> navbar;

    public List<OrderItem> getNavbar() {
        return navbar;
    }

    public void setNavbar(List<OrderItem> navbar) {
        this.navbar = navbar;
    }

    public void addItem(OrderItem item) {
        if (item == null) return;
        if (navbar == null)
            navbar = new ArrayList<>();
        navbar.add(item);
    }

    public static class OrderItem {

        private String id;

        private String tag;

        private List<InnerItem> list;

        public void addItem(InnerItem item) {
            if (item == null) return;
            if (list == null)
                list = new ArrayList<>();
            list.add(item);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public List<InnerItem> getList() {
            return list;
        }

        public void setList(List<InnerItem> list) {
            this.list = list;
        }

        public static class InnerItem {

            private String id;

            private String date;

            private String status;

            private String image_url;

            private String description;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class InnerItemExt extends InnerItem {

            private String productid;

            private String delcom;

            private String deliverno;

            private String paydate;

            private String deliverdate;

            private String reachdate;

            public String getProductid() {
                return productid;
            }

            public void setProductid(String productid) {
                this.productid = productid;
            }

            public String getDelcom() {
                return delcom;
            }

            public void setDelcom(String delcom) {
                this.delcom = delcom;
            }

            public String getDeliverno() {
                return deliverno;
            }

            public void setDeliverno(String deliverno) {
                this.deliverno = deliverno;
            }

            public String getPaydate() {
                return paydate;
            }

            public void setPaydate(String paydate) {
                this.paydate = paydate;
            }

            public String getDeliverdate() {
                return deliverdate;
            }

            public void setDeliverdate(String deliverdate) {
                this.deliverdate = deliverdate;
            }

            public String getReachdate() {
                return reachdate;
            }

            public void setReachdate(String reachdate) {
                this.reachdate = reachdate;
            }
        }
    }
}
