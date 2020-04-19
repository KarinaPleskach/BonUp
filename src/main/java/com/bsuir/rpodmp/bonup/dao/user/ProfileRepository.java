package com.bsuir.rpodmp.bonup.dao.user;

import com.bsuir.rpodmp.bonup.model.user.Profile;
import com.bsuir.rpodmp.bonup.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
}
