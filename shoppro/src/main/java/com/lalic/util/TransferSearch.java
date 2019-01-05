package com.lalic.util;

import com.alibaba.fastjson.JSON;
import com.lalic.http.SimpleHttp;
import com.lalic.model.body.DeliverSearch;

public class TransferSearch {

    public static Object SearchById(String com, String transferid) {

        try {
            String url = DeliverComMapping.getSearchUrl() + DeliverComMapping.getDeliverCom(com) + "&postid=" + transferid;

            SimpleHttp.Response response = SimpleHttp.get(url, null);

            if (response.getCode() >= 200 && response.getCode() <= 299) {

                DeliverSearch deliverSearch = JSON.parseObject(response.getBody(), DeliverSearch.class);
                if (deliverSearch.getStatus().equals("200")) {
                    return deliverSearch.getData();
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(SearchById("zt", "75119489155366"));
    }

}
