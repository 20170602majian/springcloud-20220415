package com.majian.fegin.hystrix;

import com.majian.fegin.service.MemberFegin;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class     ProviderFallBackFactory implements FallbackFactory<MemberFegin> {
    @Override
    public MemberFegin create(Throwable cause) {


        return new MemberFegin() {
            @Override
            public String get(Integer id, String name) {

                return null;
            }

            @Override
            public String getPost(Map map) {
                log.info("调用参数为： -----> "+map);

                if (cause instanceof  Exception && cause instanceof HystrixTimeoutException){
                    log.error("出现了HystrixTimeoutException一场：----》"+cause);
                    return "远程服务报错";
                }else if (cause instanceof  Exception){
                    log.error("出错了，未知错误");
                    return " 出错了，未知错误！！！"+cause;
                }else {
                    return null;
                }
            }

            @Override
            public String getGet(Map map) {
                return "服务调用异常";
            }

            /*@Override
            public String sendMessage(String message) {
                return "服务调用异常";
            }*/
        };
    }
}
