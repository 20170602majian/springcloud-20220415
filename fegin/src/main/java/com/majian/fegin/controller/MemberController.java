package com.majian.fegin.controller;

import com.majian.fegin.entity.Person;
import com.majian.fegin.service.MemberFegin;
import com.majian.fegin.service.SendMessageFegin;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/fegin")
@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberFegin memberFegin;
    @Autowired
    private SendMessageFegin sendMessage;

    @GetMapping("/member/getId")
    @ResponseBody
    //@HystrixCommand(fallbackMethod = "getFallBack")
    public String get ( Integer id,String name){
        String s = memberFegin.get(id,name);
        //System.out.println(s);
        log.info(s);
        return s;
    }
    public void getFallBack ( Integer id,String name){
        //String s = memberFegin.get(id,name);
        //System.out.println(s);
        //log.info("controller进行降级");
        log.info("controller进行降级");
    }
    @GetMapping("/member/getPost")
    @ResponseBody
    public String getPost (@RequestParam Map map){
        String s = memberFegin.getPost(map);
        //System.out.println(s);
        log.info(s);
        return s;
    }
    @GetMapping("/member/getPostGet")
    @ResponseBody
    public String getGet (@RequestParam Map map){
        System.out.println(map);
        String s = memberFegin.getGet(map);
        //System.out.println(s);
        log.info(s);
        return s;
    }

    @GetMapping("/member/sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam("message") String message){
        return sendMessage.sendMessage(message);
    }



    @GetMapping("/member/object")
    @ResponseBody
    public String sendMessage(Person person){
        Map  pMap = new HashMap();
        pMap.put("pAge",person.getPAge());
        pMap.put("pId",person.getPId());
        pMap.put("pName",person.getPName());
        return sendMessage.sendMessageForObject(pMap);
    }

    @GetMapping("/member/sendFanoutMennsge")
    @ResponseBody
    public String sendFanoutMennsge(Person person){
        Map  pMap = new HashMap();
        pMap.put("pAge",person.getPAge());
        pMap.put("pId",person.getPId());
        pMap.put("pName",person.getPName());
        return sendMessage.sendFanoutMessage(pMap);
    }

    @GetMapping("/member/sendTopicMessage")
    @ResponseBody
    public String sendTopicMessage(@RequestParam("message") String message , @RequestParam("binding") String binding){
        return sendMessage.sendTopicMessage(message, binding);
    }

}
