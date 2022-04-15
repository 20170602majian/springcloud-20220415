package com.majian.fegin.hystrix;

import com.majian.fegin.service.MemberFegin;
import com.majian.fegin.service.SendMessageFegin;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SendMesageFallBackFactory implements FallbackFactory<SendMessageFegin> {
    @Override
    public SendMessageFegin create(Throwable cause) {
        return new SendMessageFegin() {
            @Override
            public String sendMessage(String message) {
                return "服务调用异常"+cause;
            }

            @Override
            public String sendMessageForObject(Map map) {
                return "服务调用异常"+cause;
            }

            @Override
            public String sendFanoutMessage(Map map) {
                return "服务调用异常"+cause;
            }

            @Override
            public String sendTopicMessage(String message, String binding) {
                return "服务调用异常"+cause;
            }
        };
    }

}
