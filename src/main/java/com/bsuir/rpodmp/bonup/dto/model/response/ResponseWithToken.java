package com.bsuir.rpodmp.bonup.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithToken {
    private Boolean isSuccess;
    private String message;
    private String token;
}
