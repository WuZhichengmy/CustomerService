package com.example.customerservice.msgmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class MessageDto {

    @Builder
    public MessageDto(Long id, Byte type, String content, String senderId, String rcvId, LocalDateTime time, Long conversationId) {
        this.id = id;
        this.type = type;
        this.content = content;
        this.senderId = senderId;
        this.rcvId = rcvId;
        this.time = time;
        this.conversationId = conversationId;
    }

    private Long id;

    private Byte type;

    private String content;

    private String senderId;

    private String rcvId;

    private LocalDateTime time;

    private Long conversationId;
}
