package com.bsuir.rpodmp.bonup.dto.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseWithMessage {
    private Boolean error;
    private String message;
}
