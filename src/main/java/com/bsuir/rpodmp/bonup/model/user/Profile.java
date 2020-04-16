package com.bsuir.rpodmp.bonup.model.user;

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
@Table(name = "profile")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Profile extends AbstractEntity {
    @Column(nullable = false)
    @NonNull
    private String name;

    @Builder
    public Profile(@NonNull String name) {
        this.name = name;
    }
}
