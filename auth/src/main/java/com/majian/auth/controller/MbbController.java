package com.majian.auth.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.majian.auth.config.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mbb")
public class MbbController {

    @GetMapping("/init")
    public ResponseEntity init(){
        return ResponseEntity.ok(new CommonResult()
                .setCode("2000")
                .setMessage("请求成功"));
    }

}


