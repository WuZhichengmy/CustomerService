package com.example.customerservice.instmsg.service.rocketmq;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import com.example.customerservice.instmsg.websocket.WebsocketHandler;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Component
@RocketMQMessageListener(consumerGroup = "msg", topic = "sendfile")
public class MessageListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String str) {
        System.out.println("receive");
        SimpleMessageDto simpleMessageDto = JacksonUtil.toObj(str, SimpleMessageDto.class);
        WebsocketHandler.sendToSpecUser(simpleMessageDto);
    }
}
