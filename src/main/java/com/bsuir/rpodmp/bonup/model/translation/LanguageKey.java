package com.bsuir.rpodmp.bonup.model.translation;

import com.bsuir.rpodmp.bonup.model.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "language_key")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class LanguageKey extends AbstractEntity {
    @Column(nullable = false)
    @NonNull
    private String key;
}
