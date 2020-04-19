package com.bsuir.rpodmp.bonup.exception.validation;

public class NoSuchUserException extends BaseValidationException {

    public NoSuchUserException(String lang) {
        super(lang);
        setKey("message.noUserExist");
    }

}
