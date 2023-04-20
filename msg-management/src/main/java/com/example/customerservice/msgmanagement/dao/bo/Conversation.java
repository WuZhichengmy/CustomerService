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
    public Conversation(Long id, Long consumerId, Long staffId, List<Message> messageList, MessageDao messageDao, Byte status) {
        this.id = id;
        this.consumerId = consumerId;
        this.staffId = staffId;
        this.messageList = messageList;
        this.messageDao = messageDao;
        this.status = status;
    }

    @Getter
    private Long id;

    @Getter
    private Long consumerId;

    @Getter
    private Long staffId;

    @Getter
    @Setter
    private Byte status;

    @Setter
    private List<Message> messageList;

    @Setter
    @JsonIgnore
    private MessageDao messageDao;

    public List<Message> getMessageList() {
        messageList = messageDao.findByConversationId(id);
        return messageList;
    }
}
