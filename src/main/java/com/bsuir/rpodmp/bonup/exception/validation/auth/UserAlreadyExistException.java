package com.bsuir.rpodmp.bonup.exception.validation.auth;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class UserAlreadyExistException extends BaseValidationException {
    public UserAlreadyExistException() {
        setKey("message.userExist");
    }
}
