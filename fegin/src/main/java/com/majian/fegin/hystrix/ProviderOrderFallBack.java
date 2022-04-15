package com.majian.fegin.hystrix;

import com.majian.fegin.service.MemberFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
@Slf4j
public class ProviderOrderFallBack implements MemberFegin {
    @Override
    public String get(Integer id, String name) {
        //log.info("get方法fegin进行降级处理");
        System.out.println("get方法fegin进行降级处理");
        return null;
    }

    @Override
    public String getPost(Map map) {
        //log.info("getPost方法fegin进行降级处理");
        System.out.println("getPost方法fegin进行降级处理");
        return "请求超时";
    }

    @Override
    public String getGet(Map map) {
        return "请求出错";
    }

   /* @Override
    public String sendMessage(String message) {
        return "服务调用异常";
    }*/
}
