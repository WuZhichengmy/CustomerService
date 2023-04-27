package com.example.customerservice.instmsg.websocket;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.instmsg.service.MessageService;
import com.example.customerservice.instmsg.service.dto.SimpleMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint(value = "/websocket/{usrid}")
@Component
public class WebsocketHandler {
    private static Map<String, Session> sessionMap = new HashMap<>();

    private Session session;

    private String userId;

    private static MessageService messageService;

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
        System.out.println(userId);
    }

    @OnClose
    public void onClose(){
        sessionMap.remove(this.userId);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("usrid") String userId){
        SimpleMessageDto simpleMessageDto = JacksonUtil.toObj(message, SimpleMessageDto.class);
        Session toSession = sessionMap.get(simpleMessageDto.getRcvId());
        if(simpleMessageDto.getType() == 0){
            // 文本消息
            toSession.getAsyncRemote().sendText(message);
            // 数据库操作
            messageService.insertMessage(simpleMessageDto);
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
