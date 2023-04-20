package com.example.customerservice.usrmanagement.dao.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Staff {

    @Builder
    public Staff(Long id, String username, Byte type, Integer conNum, Long shopId, Byte status) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.conNum = conNum;
        this.shopId = shopId;
        this.status = status;
    }

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    @Setter
    private Byte type;

    @Getter
    @Setter
    private Integer conNum;

    @Getter
    private Long shopId;

    @Getter
    @Setter
    private Byte status;
}
