package com.majian.auth.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//第三方服务

@RestController
public class MainController {

    @GetMapping("/oauth2/api/me")
    public Authentication me() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        return authentication;
    }


    @GetMapping("/oauth2/api/read/xxoo")
    public Authentication xxoo() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("xxpp");

        return authentication;
    }

    @GetMapping("/oauth2/api/write/xxoo")
    public Authentication write() {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("write");
        return authentication;
    }
}