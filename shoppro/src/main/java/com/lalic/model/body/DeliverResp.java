package com.lalic.model.body;

public class DeliverResp {

    private Inner orders;

    private Object logistics_detail;

    public Inner getOrders() {
        return orders;
    }

    public void setOrders(Inner orders) {
        this.orders = orders;
    }

    public Object getLogistics_detail() {
        return logistics_detail;
    }

    public void setLogistics_detail(Object logistics_detail) {
        this.logistics_detail = logistics_detail;
    }

    public static class Inner {
        private String id;

        private String style;

        private String title;

        private String image;

        private String status;

        private String num;

        private String company;

        private String express_number;

        private String order_number;

        private String due_date;

        private String phone;

        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getExpress_number() {
            return express_number;
        }

        public void setExpress_number(String express_number) {
            this.express_number = express_number;
        }

        public String getOrder_number() {
            return order_number;
        }

        public void setOrder_number(String order_number) {
            this.order_number = order_number;
        }

        public String getDue_date() {
            return due_date;
        }

        public void setDue_date(String due_date) {
            this.due_date = due_date;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
