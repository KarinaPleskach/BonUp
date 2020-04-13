package com.bsuir.rpodmp.bonup.model.translation;

import com.bsuir.rpodmp.bonup.model.AbstractEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "language")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Language extends AbstractEntity {
    @Column(nullable = false)
    @NonNull
    private String lang;
    @Column(nullable = false)
    @NonNull
    private String name;
    @Column(nullable = false)
    @NonNull
    private Boolean active;

    @Builder
    public Language(@NonNull String lang, @NonNull String name, @NonNull Boolean active) {
        this.lang = lang;
        this.name = name;
        this.active = active;
    }

}
