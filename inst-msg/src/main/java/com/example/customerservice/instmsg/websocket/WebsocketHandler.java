package com.example.customerservice.instmsg.websocket;

import com.example.core.util.JacksonUtil;
import com.example.core.util.RedisUtil;
import com.example.customerservice.instmsg.config.ValueConfig;
import com.example.customerservice.instmsg.service.MessageService;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{usrid}")
@Component
public class WebsocketHandler {
    private static Map<String, Session> sessionMap = new HashMap<>();
    private Session session;
    private String userId;
    private static MessageService messageService;

    private RedisUtil redisUtil;

    private RocketMQTemplate rocketMQTemplate;

    private String instanceName;

    private static ApplicationContext applicationContext;

    // 一批消息中消息的数量，用于实现消息的分批存入数据库
    private static final Integer MESSAGE_COUNT = 10;

    // 一批消息，用于实现消息的分批存入数据库
    private List<SimpleMessageDto> messageDtos;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebsocketHandler.applicationContext = applicationContext;
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        WebsocketHandler.messageService = messageService;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("usrid") String userId){
        this.session=session;
        this.userId=userId;

        sessionMap.put(userId, session);
        this.session.getAsyncRemote().sendText(userId+"已上线");

        ValueConfig valueConfig = (ValueConfig) applicationContext.getBean(ValueConfig.class);
        redisUtil = applicationContext.getBean(RedisUtil.class);
        rocketMQTemplate = applicationContext.getBean(RocketMQTemplate.class);
        instanceName = valueConfig.getInstanceName();

        redisUtil.set(userId, instanceName, -1);

        messageDtos = new ArrayList<>();

        System.out.println(instanceName);
        System.out.println(userId);
    }

    @OnClose
    public void onClose(){
        sessionMap.remove(this.userId);
        redisUtil.del(userId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("usrid") String userId){
        SimpleMessageDto simpleMessageDto = JacksonUtil.toObj(message, SimpleMessageDto.class);
        Session toSession = sessionMap.get(simpleMessageDto.getRcvId());
        if(simpleMessageDto.getType() == 0){
            // 文本消息
            if(toSession != null){
                // 在本地
                // 每接收一条消息就将其存入数据库
//                toSession.getAsyncRemote().sendText(message);
//                messageService.insertMessage(simpleMessageDto);
                // 将消息分批存入数据库
                messageDtos.add(simpleMessageDto);
                toSession.getAsyncRemote().sendText(message);
                if(messageDtos.size() == MESSAGE_COUNT) {
                    // 达到制定数量，将消息存入数据库
                    messageService.insertBatchMessages(messageDtos);
                    messageDtos = new ArrayList<>();
                }
            } else {
                Message<String> remoteMsg = MessageBuilder.withPayload(message).build();
                String tag = (String) redisUtil.get(simpleMessageDto.getRcvId());
                rocketMQTemplate.send("remotemsg:"+tag, remoteMsg);
            }
            // 数据库操作
        } else {
            this.session.getAsyncRemote().sendText("error");
        }
    }

    /**
     * 发送给指定用户
     * @param simpleMessageDto
     */
    public static void sendToSpecUser(SimpleMessageDto simpleMessageDto){
        Session toSession = sessionMap.get(simpleMessageDto.getRcvId());
        toSession.getAsyncRemote().sendText(JacksonUtil.toJson(simpleMessageDto));
        WebsocketHandler.messageService.insertMessage(simpleMessageDto);
    }
}
