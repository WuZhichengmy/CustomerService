package com.example.customerservice.usrmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ConversationDto {

    @Builder
    public ConversationDto(Long id, String customerId, String staffId, Byte status) {
        this.id = id;
        this.customerId = customerId;
        this.staffId = staffId;
        this.status = status;
    }

    private Long id;

    private String customerId;

    private String staffId;

    private Byte status;
}
