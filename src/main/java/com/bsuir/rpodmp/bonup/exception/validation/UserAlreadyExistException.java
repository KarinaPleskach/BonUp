package com.bsuir.rpodmp.bonup.exception.validation;

public class UserAlreadyExistException extends BaseValidationException {

    public UserAlreadyExistException(String lang) {
        super(lang);
        setKey("message.userExist");
    }

}
