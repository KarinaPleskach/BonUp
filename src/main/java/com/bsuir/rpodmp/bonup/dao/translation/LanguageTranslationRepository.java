package com.bsuir.rpodmp.bonup.dao.translation;

import com.bsuir.rpodmp.bonup.model.translation.Language;
import com.bsuir.rpodmp.bonup.model.translation.LanguageKey;
import com.bsuir.rpodmp.bonup.model.translation.LanguageTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageTranslationRepository extends JpaRepository<LanguageTranslation, Long> {
    Optional<LanguageTranslation> findByLanguageAndLanguageKey(Language language, LanguageKey languageKey);
}
