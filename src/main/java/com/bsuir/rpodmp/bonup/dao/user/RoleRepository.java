package com.bsuir.rpodmp.bonup.dao.user;

import com.bsuir.rpodmp.bonup.model.user.Role;
import com.bsuir.rpodmp.bonup.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByDescription(UserRole userRole);
}
