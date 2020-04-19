package com.bsuir.rpodmp.bonup.exception.translation;

import com.bsuir.rpodmp.bonup.exception.BaseException;

public class NoSuchLanguageException extends BaseException {

    public NoSuchLanguageException(String lang) {
        super(lang);
        setKey("message.noSuchLanguage");
    }

}
