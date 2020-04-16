package com.bsuir.rpodmp.bonup.dao.translation;

import com.bsuir.rpodmp.bonup.model.translation.LanguageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageKeyRepository extends JpaRepository<LanguageKey, Long> {
    Optional<LanguageKey> findByKey(String key);
}
