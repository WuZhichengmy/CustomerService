package com.example.customerservice.usrmanagement.dao.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consumer {

    @Builder
    public Consumer(Long id, String username, Byte priority) {
        this.id = id;
        this.username = username;
        this.priority = priority;
    }

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    @Setter
    private Byte priority;

}
