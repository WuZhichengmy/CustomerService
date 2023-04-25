package com.example.customerservice.msgmanagement.controller;

import com.example.core.model.ReturnNo;
import com.example.core.model.dto.PageDto;
import com.example.core.util.JacksonUtil;
import com.example.customerservice.msgmanagement.MsgManagementApplication;
import com.example.customerservice.msgmanagement.controller.vo.ConversationVo;
import com.example.customerservice.msgmanagement.service.ConversationService;
import com.example.customerservice.msgmanagement.service.MessageService;
import com.example.customerservice.msgmanagement.service.dto.ConversationDto;
import com.example.customerservice.msgmanagement.service.dto.MessageDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MsgManagementApplication.class)
@AutoConfigureMockMvc
public class ConversionControllerTest {
    private static final String BASE_URL = "/conversation";
    private static final String CID_URL = "/{cid}";
    private static final String MESSAGES_URL = "/{cid}/messages";

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testInsertConversation() throws Exception {
        // Given
        ConversationVo conversationVo = ConversationVo.builder().staffId("022e2106cf4a60f7b0c02b9cac92f060").customerId("0527294a96364b3f2d06bc7ed9539d71").build();

        // When
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JacksonUtil.toJson(conversationVo)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testCloseConversation() throws Exception {
        // Given
        Long cid = 101L;

        // When
        mockMvc.perform(put(BASE_URL + CID_URL + "/close", cid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testGetConversationById() throws Exception {
        Long cid = 1L;
        // When
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + CID_URL, cid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testDeleteConversation() throws Exception {
        // Given
        Long cid = 102L;

        // When
        mockMvc.perform(delete(BASE_URL + CID_URL, cid)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindMsgsByConversation() throws Exception {
        // Given
        Long cid = 3L;
        Integer page = 0;
        Integer pageSize = 10;

        // When
        mockMvc.perform(get(BASE_URL + MESSAGES_URL, cid)
                        .param("page", page.toString())
                        .param("pageSize", pageSize.toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

    }
}
