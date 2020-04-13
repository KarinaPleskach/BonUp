package com.bsuir.rpodmp.bonup.service.user.impl;

import com.bsuir.rpodmp.bonup.dao.user.RoleRepository;
import com.bsuir.rpodmp.bonup.dao.user.UserRepository;
import com.bsuir.rpodmp.bonup.dto.converter.user.AuthUserDtoToUserConverter;
import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.exception.RoleNotFoundException;
import com.bsuir.rpodmp.bonup.exception.validation.NullValidationException;
import com.bsuir.rpodmp.bonup.exception.validation.auth.NullRegistrationException;
import com.bsuir.rpodmp.bonup.exception.validation.auth.UserAlreadyExistException;
import com.bsuir.rpodmp.bonup.model.user.Role;
import com.bsuir.rpodmp.bonup.model.user.User;
import com.bsuir.rpodmp.bonup.model.user.UserRole;
import com.bsuir.rpodmp.bonup.service.user.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthUserDtoToUserConverter authUserDtoToUserConverter;

    @Override
    public void saveUser(AuthUserDto userDto) {
        validateAuthUserDto(userDto);
        User user = authUserDtoToUserConverter.convert(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByDescription(UserRole.ROLE_MINIMAL_USER)
                .orElseThrow(RoleNotFoundException::new);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    private void validateAuthUserDto(AuthUserDto authUserDto) {
        if (Objects.isNull(authUserDto)) {
            throw new NullValidationException();
        }
        if (Objects.isNull(authUserDto.getEmail()) || Objects.isNull(authUserDto.getPassword())) {
            throw new NullRegistrationException();
        }
        if (userRepository.findByEmail(authUserDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
    }

}
