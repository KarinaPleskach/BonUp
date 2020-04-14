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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor @Getter @Setter
@ToString(callSuper = true, exclude = {})
public class User extends AbstractEntity {
    @Column(nullable = false, unique = true)
    @NonNull
    private String email;
    @Column(nullable = false)
    @NonNull
    private String password;
    @Column(name = "verify_mail")
    private boolean verifyMail;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
    }

    @Builder
    public User(String email, String password,
                boolean verifyMail) {
        this(email, password);
        this.verifyMail = verifyMail;
    }
}
