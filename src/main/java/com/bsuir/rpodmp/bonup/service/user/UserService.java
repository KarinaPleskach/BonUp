package com.bsuir.rpodmp.bonup.service.user;

import com.bsuir.rpodmp.bonup.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> showAll();

    Optional<User> findByMail(String mail);

    boolean isUserPresent(String mail);

}
