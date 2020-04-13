package com.bsuir.rpodmp.bonup.service.translation;

import com.bsuir.rpodmp.bonup.dto.model.translation.TranslationKeyDto;

public interface TranslationService {
    void changeActiveLanguage(TranslationKeyDto translationKeyDto);
}
