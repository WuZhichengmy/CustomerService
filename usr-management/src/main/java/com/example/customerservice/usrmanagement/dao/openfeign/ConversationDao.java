package com.example.customerservice.usrmanagement.dao.openfeign;

import com.example.core.model.InternalReturnObject;
import com.example.core.model.dto.PageDto;
import com.example.customerservice.usrmanagement.service.dto.ConversationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;

@FeignClient("msg-management")
public interface ConversationDao {
    @GetMapping("/internal/staff/{sid}/conversation")
    InternalReturnObject<PageDto<ConversationDto>> findConversationsByStaffId(@PathVariable("sid") String sid,
                                                                              @RequestParam Integer page,
                                                                              @RequestParam Integer pageSize);

    @GetMapping("/internal/customer/{sid}/conversation")
    InternalReturnObject<PageDto<ConversationDto>> findConversationsByCustomerId(@PathVariable("sid") String sid,
                                                                                 @RequestParam Integer page,
                                                                                 @RequestParam Integer pageSize);
}
