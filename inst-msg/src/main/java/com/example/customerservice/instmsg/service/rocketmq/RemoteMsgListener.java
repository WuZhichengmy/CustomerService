package com.example.customerservice.instmsg.service.rocketmq;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import com.example.customerservice.instmsg.websocket.WebsocketHandler;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "remotemsg", topic = "remotemsg",
        selectorExpression = "${websocket.service.instance}", messageModel = MessageModel.BROADCASTING)
public class RemoteMsgListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String str) {
        SimpleMessageDto simpleMessageDto = JacksonUtil.toObj(str, SimpleMessageDto.class);
        WebsocketHandler.sendToSpecUser(simpleMessageDto);
    }
}
