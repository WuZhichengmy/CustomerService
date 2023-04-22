package com.example.customerservice.usrmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class CustomerDto {

    @Builder
    public CustomerDto(String id, String username, Byte priority) {
        this.id = id;
        this.username = username;
        this.priority = priority;
    }

    private String id;

    private String username;

    private Byte priority;
}
