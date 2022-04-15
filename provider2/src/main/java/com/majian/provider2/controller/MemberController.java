package com.majian.provider2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequestMapping ("/member")
@Controller
public class MemberController {

    @GetMapping("/getId")
    @ResponseBody
    public String get (Integer id, String name, HttpServletRequest request){
        System.out.printf("调用成功！！！"+id+name);
        return "ssss";
    }

    @PostMapping("/getPost")
    @ResponseBody
    public String getPost (@RequestBody Map map){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return "ssss";
    }

    static int q = 0;
    static Object object = new Object();
    public static void main(String[] args) {
    for (int i = 0; i <300 ; i++) {
       new Thread(() -> {
           for (int j = 0; j < 300; j++) {
               synchronized (object){
                   q++;
               }
           }
       }).start();
     }
        System.out.println(q);
    }


    @RequestMapping(value = "/getGet",method = RequestMethod.GET)
    @ResponseBody
    public String getGet (@RequestParam Map map){
        System.out.println("provider被调用 参数为-》"+map);
        return map.toString();
    }
}
