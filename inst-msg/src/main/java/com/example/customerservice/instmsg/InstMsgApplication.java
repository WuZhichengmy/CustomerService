package com.example.customerservice.instmsg;

import com.example.customerservice.instmsg.websocket.WebsocketHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.example.core", "com.example.customerservice.instmsg"})
@EnableDiscoveryClient
@EnableFeignClients
public class InstMsgApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(InstMsgApplication.class, args);
        WebsocketHandler.setApplicationContext(run);
    }
}