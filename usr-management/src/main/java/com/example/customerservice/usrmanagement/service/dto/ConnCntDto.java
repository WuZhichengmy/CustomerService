package com.example.customerservice.usrmanagement.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ConnCntDto {
    @Builder
    public ConnCntDto (Integer conNum){
        this.conNum = conNum;
    }

    private Integer conNum;
}
