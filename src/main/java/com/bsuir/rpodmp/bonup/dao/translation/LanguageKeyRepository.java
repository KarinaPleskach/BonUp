package com.bsuir.rpodmp.bonup.dao.translation;

import com.bsuir.rpodmp.bonup.model.translation.LanguageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageKeyRepository extends JpaRepository<LanguageKey, Long> {
    LanguageKey findByKey(String key);
}
