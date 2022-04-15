package com.majian.provider.controller;

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
        System.out.println(map);
        return "ssss";
    }
    @RequestMapping(value = "/getGet",method = RequestMethod.GET)
    @ResponseBody
    public String getGet (@RequestParam Map map){
        System.out.println("provider被调用 参数为-》"+map);
        return map.toString();
    }
}
