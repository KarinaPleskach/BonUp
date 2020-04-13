package com.bsuir.rpodmp.bonup.service.translation.impl;

import com.bsuir.rpodmp.bonup.dao.translation.LanguageRepository;
import com.bsuir.rpodmp.bonup.dto.model.translation.TranslationKeyDto;
import com.bsuir.rpodmp.bonup.exception.validation.NullValidationException;
import com.bsuir.rpodmp.bonup.exception.validation.translation.NoSuchLanguageException;
import com.bsuir.rpodmp.bonup.model.translation.Language;
import com.bsuir.rpodmp.bonup.service.translation.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public void changeActiveLanguage(TranslationKeyDto translationKeyDto) {
        validateTranslationKeyDto(translationKeyDto);
        Language language = languageRepository.findByLang(translationKeyDto.getLanguageKey())
                .orElseThrow(NoSuchLanguageException::new);
        for (Language lang : languageRepository.findAll()) {
            lang.setActive(false);
        }
        language.setActive(true);
    }

    private void validateTranslationKeyDto(TranslationKeyDto translationKeyDto) {
        if (Objects.isNull(translationKeyDto) || Objects.isNull(translationKeyDto.getLanguageKey())) {
            throw new NullValidationException();
        }
    }

}
