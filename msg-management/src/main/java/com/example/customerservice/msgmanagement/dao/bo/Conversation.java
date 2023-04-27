package com.example.customerservice.msgmanagement.dao.bo;

import com.example.customerservice.msgmanagement.dao.MessageDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Conversation {

    @Builder
    public Conversation(Long id, String consumerId, String staffId, List<InstMessage> instMessageList, MessageDao messageDao, Byte status) {
        this.id = id;
        this.customerId = consumerId;
        this.staffId = staffId;
        this.instMessageList = instMessageList;
        this.messageDao = messageDao;
        this.status = status;
    }

    @Getter
    private Long id;

    @Getter
    private String customerId;

    @Getter
    private String staffId;

    @Getter
    @Setter
    private Byte status;

    @Setter
    private List<InstMessage> instMessageList;

    @Setter
    @JsonIgnore
    private MessageDao messageDao;

    public List<InstMessage> getInstMessageList() {
        instMessageList = messageDao.findByConversationId(id);
        return instMessageList;
    }
}
