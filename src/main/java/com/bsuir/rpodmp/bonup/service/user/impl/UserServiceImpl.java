package com.bsuir.rpodmp.bonup.service.user.impl;

import com.bsuir.rpodmp.bonup.dao.user.UserRepository;
import com.bsuir.rpodmp.bonup.model.user.User;
import com.bsuir.rpodmp.bonup.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Dao section
    @Override
    public List<User> showAll() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> findByMail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public boolean isUserPresent(String email) {
        return findByMail(email).isPresent();
    }
    //--------------------------------------------------

    // Current user section
    @Override
    public Optional<User> getCurrentUser() {
        return findByMail(getCurrentUserMail());
    }

    @Override
    public String getCurrentUserMail() {
        return getCurrentAuthentication().getName();
    }

    private Authentication getCurrentAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Optional.ofNullable(auth)
//                .orElseThrow(NoCurrentUserException::new);
        return auth;
    }

//    @Override
//    public boolean isCurrentUser(String mail) throws NoCurrentUserException {
//        if (Objects.isNull(mail)) {
//            return false;
//        }
//        return mail.equals(getCurrentUserMail());
//    }
    //--------------------------------------------------
}
