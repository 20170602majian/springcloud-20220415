package com.majian.eureka.config;


import com.netflix.appinfo.InstanceInfo;
import org.slf4j.MarkerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EurekaStateChangeListener {

    /**
     * 服务下线事件
     * @param event
     */
    @EventListener
    public void listenDown(EurekaInstanceCanceledEvent event){


        System.out.println("DOWN"+ "服务ID：" + event.getServerId() + "\t" + "服务实例：" + event.getAppName() + "\t服务下线");

      //  System.out.println(event.getServerId() + "\t" + event.getAppName() + "服务下线");
    }





    //@EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        System.err.println(instanceInfo.getAppName() + "进行注册");
    }

   // @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        System.err.println(event.getServerId() + "\t" + event.getAppName() + " 服务进行续约");
    }

    //@EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        System.err.println("注册中心 启动");
    }

    //@EventListener
    public void listen(EurekaServerStartedEvent event) {
        System.err.println("Eureka Server 启动");
    }

}
