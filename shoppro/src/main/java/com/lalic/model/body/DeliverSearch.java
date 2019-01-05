package com.lalic.model.body;

import java.util.List;

public class DeliverSearch {

    String status;

    List<Inner> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Inner> getData() {
        return data;
    }

    public void setData(List<Inner> data) {
        this.data = data;
    }

    public static class Inner{

        String time;

        String context;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }
}
