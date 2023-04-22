package com.example.customerservice.usrmanagement.dao.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {

    @Builder
    public Customer(String id, String username, Byte priority) {
        this.id = id;
        this.username = username;
        this.priority = priority;
    }

    @Getter
    private String id;

    @Getter
    private String username;

    @Getter
    @Setter
    private Byte priority;

}
