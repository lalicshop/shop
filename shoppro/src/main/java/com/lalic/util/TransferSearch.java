package com.lalic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferSearch {


    public static Object SearchById(String transferid)
    {
        List list=new ArrayList();
        for (int i = 0; i < 3; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("time","2018-09-13 20:55:00");
            map.put("content","到达西安");
            list.add(map);
        }
        return list;
    }

}
