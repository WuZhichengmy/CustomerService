package com.example.customerservice.msgmanagement.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstMessage {

    @Builder
    public InstMessage(Long id, Byte type, String content, String senderId, String rcvId, LocalDateTime time, Long conversationId) {
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
    private String senderId;

    @Getter
    private String rcvId;

    @Getter
    private LocalDateTime time;

    @Getter
    private Long conversationId;

}
