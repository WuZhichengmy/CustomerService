package com.example.customerservicesystem.dao.bo;

import com.example.customerservicesystem.dao.ConversationDao;
import com.example.customerservicesystem.dao.MessageDao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consumer {

    @Builder
    public Consumer(Long id, String username, Byte priority, List<Conversation> conversationList) {
        this.id = id;
        this.username = username;
        this.priority = priority;
        this.conversationList = conversationList;
    }

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    @Setter
    private Byte priority;

    @Setter
    private List<Conversation> conversationList;

    @Setter
    @JsonIgnore
    private ConversationDao conversationDao;

    public List<Conversation> getConversationList() {
        conversationList = conversationDao.findByConsumerId(id);
        return conversationList;
    }

}
