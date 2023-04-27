package com.example.customerservice.instmsg.service;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    public MessageService(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void insertMessage(SimpleMessageDto simpleMessageDto) {
        String toJson = JacksonUtil.toJson(simpleMessageDto);
        Message build = MessageBuilder.withPayload(toJson).build();
        rocketMQTemplate.send("insertmsg", build);
    }
}
