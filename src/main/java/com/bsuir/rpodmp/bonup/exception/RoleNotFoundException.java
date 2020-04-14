package com.bsuir.rpodmp.bonup.exception;

import com.bsuir.rpodmp.bonup.exception.validation.BaseValidationException;

public class RoleNotFoundException extends BaseValidationException {
    public RoleNotFoundException() {
        setKey("message.egorDurak");
    }
}
