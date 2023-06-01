package com.example.customerservice.instmsg.service;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void insertBatchMessages(List<SimpleMessageDto> list) {
        List<Message> messages = new ArrayList<>();
        list.forEach(o->{
            String toJson = JacksonUtil.toJson(o);
            Message build = MessageBuilder.withPayload(toJson).build();
            messages.add(build);
        });
        rocketMQTemplate.syncSend("insertmsg", messages);
    }
}
