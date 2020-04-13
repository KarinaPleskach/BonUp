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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "role")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class Role extends AbstractEntity implements Serializable {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NonNull
    private UserRole description;

    @Builder
    public Role(@NonNull UserRole description) {
        this.description = description;
    }
}
