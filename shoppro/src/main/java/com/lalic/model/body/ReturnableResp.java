package com.lalic.model.body;

import java.util.ArrayList;
import java.util.List;

public class ReturnableResp {
    private List<Inner> list;

    public List<Inner> getList() {
        return list;
    }

    public void setList(List<Inner> list) {
        this.list = list;
    }

    public void addItem(Inner item) {
        if (item == null) return;
        if (list == null)
            list = new ArrayList<>();
        list.add(item);
    }

    public static class Inner {

        private String id;

        private String image_url;

        private String num;

        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
