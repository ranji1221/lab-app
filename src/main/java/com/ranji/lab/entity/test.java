package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("dep","市场部");

        Map put = new HashMap<>();
        put.put("name", "zs");
        put.put("zhiwei","jingli");
        allMap.put("data",put);


        HashMap<Object, Object> allMap1 = new HashMap<>();
        allMap1.put("dep","人事部");

        Map put1 = new HashMap<>();
        put1.put("name", "zs");
        put1.put("zhiwei","jingli");
        allMap1.put("data",put1);

        List a = new ArrayList();
        a.add(allMap);
        a.add(allMap1);
        System.out.println(JSON.toJSONString(a));
    }
}
