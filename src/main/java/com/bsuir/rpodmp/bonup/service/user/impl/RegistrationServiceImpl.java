package com.bsuir.rpodmp.bonup.service.user.impl;

import com.bsuir.rpodmp.bonup.dao.user.ProfileRepository;
import com.bsuir.rpodmp.bonup.dao.user.RoleRepository;
import com.bsuir.rpodmp.bonup.dao.user.UserRepository;
import com.bsuir.rpodmp.bonup.dto.converter.user.AuthUserDtoToUserConverter;
import com.bsuir.rpodmp.bonup.dto.model.email_code.EmailCode;
import com.bsuir.rpodmp.bonup.dto.model.password.NewPassword;
import com.bsuir.rpodmp.bonup.dto.model.user.AuthUserDto;
import com.bsuir.rpodmp.bonup.dto.model.user.EmailUser;
import com.bsuir.rpodmp.bonup.exception.exception.RoleNotFoundException;
import com.bsuir.rpodmp.bonup.exception.registration.AccessErrorException;
import com.bsuir.rpodmp.bonup.exception.registration.IncorrectLoginDataException;
import com.bsuir.rpodmp.bonup.exception.registration.InvalidEmailCodeException;
import com.bsuir.rpodmp.bonup.exception.registration.NotValidateMailException;
import com.bsuir.rpodmp.bonup.exception.validation.NoSuchUserException;
import com.bsuir.rpodmp.bonup.exception.validation.NullValidationException;
import com.bsuir.rpodmp.bonup.exception.validation.UserAlreadyExistException;
import com.bsuir.rpodmp.bonup.model.user.Profile;
import com.bsuir.rpodmp.bonup.model.user.Role;
import com.bsuir.rpodmp.bonup.model.user.User;
import com.bsuir.rpodmp.bonup.model.user.UserRole;
import com.bsuir.rpodmp.bonup.service.translation.TranslationService;
import com.bsuir.rpodmp.bonup.service.user.RegistrationService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private TranslationService translationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private AuthUserDtoToUserConverter authUserDtoToUserConverter;

    @Override
    public void createNewUser(AuthUserDto userDto, String lang) {
        validateAuthUserDto(userDto, lang);
        saveUserAndProfile(userDto);
        sendCode(userDto.getEmail(), lang);
    }

    private void saveUserAndProfile(AuthUserDto userDto) {
        User user = authUserDtoToUserConverter.convert(userDto);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        user.setPassword(sb.toString());
//        user.setPassword(Base64.getEncoder().encodeToString(user.getPassword().getBytes()));
        user.setRoles(new HashSet<Role>(Arrays.asList(getStandardRole())));
        userRepository.save(user);
        Profile profile = Profile.builder()
                .name(userDto.getName())
                .user(user)
                .build();
        profileRepository.save(profile);
    }

    private Role getStandardRole() {
        return roleRepository.findByDescription(UserRole.ROLE_USER)
                .orElseThrow(RoleNotFoundException::new);
    }

    private void validateAuthUserDto(AuthUserDto authUserDto, String lang) {
        if (Objects.isNull(authUserDto)) {
            throw new NullValidationException(lang);
        }
        if (Objects.isNull(authUserDto.getEmail()) || Objects.isNull(authUserDto.getPassword()) || Objects.isNull(authUserDto.getName())) {
            throw new NullValidationException(lang);
        }
        if (userRepository.findByEmail(authUserDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException(lang);
        }
    }

    private int generateAndSaveMailCode(String email, String lang) {
        int code = 1000 + (int) (Math.random() * (9999 - 1000 + 1));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchUserException(lang));
        user.setMailCode(String.valueOf(code));
        return code;
    }

    private void sendCode(String email, String lang) {
        int code = generateAndSaveMailCode(email, lang);

        SimpleMailMessage message = new SimpleMailMessage();

        String subject = translationService.getMessage("reg.email.subject", lang);
        String text = translationService.getMessage("reg.email.message", lang) + code;

        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }

    @Override
    public void checkMailCode(EmailCode emailCode, String lang) {
        validateEmailCodeDto(emailCode, lang);
        User user = userRepository.findByEmail(emailCode.getEmail())
                .orElseThrow(() -> new NoSuchUserException(lang));
        boolean isSuccess = emailCode.getCode().equals(user.getMailCode());
        if (!isSuccess)
            throw new InvalidEmailCodeException(lang);
        else
            user.setVerifyMail(true);
    }

    private void validateEmailCodeDto(EmailCode emailCode, String lang) {
        if (Objects.isNull(emailCode) || Objects.isNull(emailCode.getCode()) || Objects.isNull(emailCode.getEmail()))
            throw new NullValidationException(lang);
    }

    @Override
    public String generateNewToken(String email, String lang) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchUserException(lang));

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", "user");
        tokenData.put("userID", user.getId().toString());
        tokenData.put("username", user.getEmail());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

        user.setToken(token);

        return token;
    }

    @Override
    public void resendCodeAndNullToken(EmailUser emailUser, String lang) {
        validateEmailUserDto(emailUser, lang);
        User user = userRepository.findByEmail(emailUser.getEmail())
                .orElseThrow(() -> new NoSuchUserException(lang));
        user.setToken(null);
        user.setVerifyMail(false);
        sendCode(emailUser.getEmail(), lang);
    }

    private void validateEmailUserDto(EmailUser emailUser, String lang) {
        if (Objects.isNull(emailUser) || Objects.isNull(emailUser.getEmail())) {
            throw new NullValidationException(lang);
        }
    }

    @Override
    public void newPassword(NewPassword newPassword, String lang) {
        validateNewPasswordDto(newPassword, lang);
        User user = userRepository.findByToken(newPassword.getToken())
                .orElseThrow(() -> new AccessErrorException(lang));

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(newPassword.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        user.setPassword(sb.toString());
    }

    private void validateNewPasswordDto(NewPassword newPassword, String lang) {
        if (Objects.isNull(newPassword) || Objects.isNull(newPassword.getPassword()) || Objects.isNull(newPassword.getToken()))
            throw new NullValidationException(lang);
    }

    @Override
    public String generateNewTokenWithToken(String token, String lang) {
        User user = userRepository.findByEmail(token)
                .orElseThrow(() -> new AccessErrorException(lang));

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("clientType", "user");
        tokenData.put("userID", user.getId().toString());
        tokenData.put("username", user.getEmail());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "abc123";
        String newToken = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();

        user.setToken(newToken);

        return newToken;
    }

    @Override
    public String login(AuthUserDto userDto, String lang) {
        validateLoginUserDto(userDto, lang);
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new NoSuchUserException(lang));

        if (!user.isVerifyMail()) {
            throw new NotValidateMailException(lang);
        }

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hashInBytes = md.digest(userDto.getPassword().getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }

        if (!sb.toString().equals(user.getPassword())) {
            throw new IncorrectLoginDataException(lang);
        }

        Profile profile = profileRepository.findByUser(user)
                .orElseThrow(() -> new NoSuchUserException(lang));
        if (!userDto.getName().equals(profile.getName())) {
            throw new IncorrectLoginDataException(lang);
        }

        return user.getToken();
    }

    private void validateLoginUserDto(AuthUserDto userDto, String lang) {
        if (Objects.isNull(userDto) || Objects.isNull(userDto.getEmail()) || Objects.isNull(userDto.getName()) || Objects.isNull(userDto.getPassword()))
            throw new NullValidationException(lang);
    }
}
