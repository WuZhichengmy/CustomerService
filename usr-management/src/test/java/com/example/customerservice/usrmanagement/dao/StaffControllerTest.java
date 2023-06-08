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
public class StaffControllerTest {
    private static final String BASE_URL = "/staff";
    private static final String ID_URL = "/{id}";
    private static final String IDEL_URL = "/{id}/idle";
    private static final String BUSY_URL = "/{id}/busy";
    private static final String CONNCNT_URL = "/{id}/conncnt";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findStaffById() throws Exception{
        String id = "022e2106cf4a60f7b0c02b9cac92f060";
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + ID_URL, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username", is("Zou")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void idleServiceStaff() throws Exception{
        String id = "022e2106cf4a60f7b0c02b9cac92f060";
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + IDEL_URL, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void busyServiceStaff() throws Exception{
        String id = "022e2106cf4a60f7b0c02b9cac92f060";
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + BUSY_URL, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void getStaffConnCnt() throws Exception{
        String id = "022e2106cf4a60f7b0c02b9cac92f060";
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + CONNCNT_URL, id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.conNum", is(0)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void putStaffConnCnt() throws Exception{
        String id = "065ba8feeb14288a9c75a36d9961d81a";
        String body = "{\"conncnt\":599}";
        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL + CONNCNT_URL, id)
                        .content(body.getBytes("utf-8"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }
}
