package com.example.customerservice.filetrans.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleMessageDto {
    String senderId;

    String rcvId;

    LocalDateTime time;

    Long conversationId;

    Byte type;

    String content;
}
