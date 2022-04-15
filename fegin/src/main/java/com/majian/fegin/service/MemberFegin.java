package com.majian.fegin.service;


import com.majian.fegin.config.FeignConfig;
import com.majian.fegin.hystrix.ProviderFallBackFactory;
import com.majian.fegin.hystrix.ProviderOrderFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient( name = "cloud-provider" ,configuration = FeignConfig.class, fallbackFactory = ProviderFallBackFactory.class)//,fallback = ProviderFallBackFactory.class
public interface MemberFegin {

    @GetMapping("/member/getId" )
    @ResponseBody
    String get(@RequestParam("id") Integer id, @RequestParam("name") String name);

    @PostMapping(value = "/member/getPost")
    @ResponseBody
    String getPost(@RequestBody Map map);

    //发送
    @RequestMapping( value = "/member/getGet",method = RequestMethod.GET)
    @ResponseBody
    String getGet(@RequestParam Map map);


}
