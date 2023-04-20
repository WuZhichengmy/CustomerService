package com.example.customerservicesystem.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ConsumerDto {

    @Builder
    public ConsumerDto(Long id, String username, Byte priority) {
        this.id = id;
        this.username = username;
        this.priority = priority;
    }

    private Long id;

    private String username;

    private Byte priority;
}
