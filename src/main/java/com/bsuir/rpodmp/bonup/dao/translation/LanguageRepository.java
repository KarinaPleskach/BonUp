package com.bsuir.rpodmp.bonup.dao.translation;

import com.bsuir.rpodmp.bonup.model.translation.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLang(String lang);
}
