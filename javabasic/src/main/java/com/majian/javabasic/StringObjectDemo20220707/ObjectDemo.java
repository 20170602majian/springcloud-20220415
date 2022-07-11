package com.majian.javabasic.StringObjectDemo20220707;

import com.alibaba.fastjson.JSONObject;
import com.majian.javabasic.entity.People;

import java.io.Serializable;
import java.util.Map;

public class ObjectDemo {

    public static void main(String[] args) {
        Object o = new Object();
        People people = new People();
        people.setName("jasgydashjdg");
        //对象与map互相转化
        ////对象转map
        Map map = JSONObject.parseObject(JSONObject.toJSONString(people), Map.class);
        //map转对象
       /* People people1 = JSONObject.parseObject(JSONObject.toJSONString(map), People.class);
        System.out.println(map);
        System.out.println(people);
        System.out.println(people1);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(people));
        System.out.println(jsonObject.getString("name"));
        Class<? extends People> aClass = people.getClass();
        System.out.println(aClass.getSimpleName());
*/
        String s1 = "111";
        String s2 = "111";
        String s3 = new String("111");
        String s4 = new String("111");
        System.out.println(s1 == s2);
        System.out.println(s4 == s3);
        System.out.println(s4.length());
        String intern = s3.intern();

    }
}
