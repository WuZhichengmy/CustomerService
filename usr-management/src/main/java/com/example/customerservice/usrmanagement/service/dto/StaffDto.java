package com.example.customerservice.usrmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class StaffDto {

    @Builder
    public StaffDto(Long id, String username, Byte type, Integer conNum, Long shopId, Byte status) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.conNum = conNum;
        this.shopId = shopId;
        this.status = status;
    }

    private Long id;

    private String username;

    private Byte type;

    private Integer conNum;

    private Long shopId;

    private Byte status;
}
