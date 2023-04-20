package com.example.customerservicesystem.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @Builder
    public Message(Long id, Byte type, String content, Long senderId, Long rcvId, LocalDateTime time, Long conversationId) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.senderId = senderId;
        this.rcvId = rcvId;
        this.time = time;
        this.conversationId = conversationId;
    }

    @Getter
    private Long id;

    @Getter
    private Byte type;

    @Getter
    private String content;

    @Getter
    private Long senderId;

    @Getter
    private Long rcvId;

    @Getter
    private LocalDateTime time;

    @Getter
    private Long conversationId;

}
