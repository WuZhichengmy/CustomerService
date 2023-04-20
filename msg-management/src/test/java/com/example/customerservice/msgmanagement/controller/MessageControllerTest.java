package com.example.customerservice.msgmanagement.controller;

import com.example.core.util.JacksonUtil;
import com.example.customerservice.msgmanagement.MsgManagementApplication;
import com.example.customerservice.msgmanagement.controller.vo.MessageVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(classes = MsgManagementApplication.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String MESSAGE = "/message";


    @Test
    public void insertMsgRecordTest() throws Exception {
        MessageVo messageVo = MessageVo.builder().type((byte) 1).senderId(1L).receiverId(1L).content("test").conversationId(1L).build();
        String s = JacksonUtil.toJson(messageVo);
        this.mockMvc.perform(MockMvcRequestBuilders.post(MESSAGE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(s))
                .andDo(MockMvcResultHandlers.print());
    }
}
