package com.bsuir.rpodmp.bonup.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseException extends RuntimeException {

    private String key;

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }
}
