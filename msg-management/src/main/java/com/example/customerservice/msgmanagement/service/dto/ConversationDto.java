package com.example.customerservice.msgmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ConversationDto {

    @Builder
    public ConversationDto(Long id, Long consumerId, Long staffId, Byte status) {
        this.id = id;
        this.consumerId = consumerId;
        this.staffId = staffId;
        this.status = status;
    }

    private Long id;

    private Long consumerId;

    private Long staffId;

    private Byte status;
}
