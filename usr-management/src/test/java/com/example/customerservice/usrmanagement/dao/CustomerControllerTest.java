package com.example.customerservice.usrmanagement.dao;

import com.example.customerservice.usrmanagement.UsrManagementApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(classes = UsrManagementApplication.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {
    private static final String BASE_URL = "/consumer";
    private static final String ID_URL = "/{id}";
    private static final String PRIORITY_URL = "/{id}/priority";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findConversationsByStaffId() throws Exception{
        String id = "0527294a96364b3f2d06bc7ed9539d71";
        String url = "/consumer/{sid}/conversations";
        mockMvc.perform(MockMvcRequestBuilders.get(url,id)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", is("Shao")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void findCustomerById() throws Exception{
        String id = "0527294a96364b3f2d06bc7ed9539d71";
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+ID_URL,id)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data", is("Shao")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void putCustomerPriority() throws Exception {
        String id = "01f976fd938aeb397e92182db537e0eb";
        String body = "{\"priority\":2}";
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + PRIORITY_URL, id)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }



}
