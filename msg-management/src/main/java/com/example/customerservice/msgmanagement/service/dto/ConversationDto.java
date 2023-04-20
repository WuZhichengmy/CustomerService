package com.example.customerservicesystem.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ConversationDto {

    @Builder
    public ConversationDto(Long id, Long consumerId, Long staffId, String consumerName, String staffName) {
        this.id = id;
        this.consumerId = consumerId;
        this.staffId = staffId;
        this.consumerName = consumerName;
        this.staffName = staffName;
    }

    private Long id;

    private Long consumerId;

    private Long staffId;

    private String consumerName;

    private String staffName;
}
