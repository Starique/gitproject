package com.tarique.webportal.backend.service;

import com.tarique.webportal.backend.persistence.domain.backend.PasswordResetToken;
import com.tarique.webportal.backend.persistence.domain.backend.User;
import com.tarique.webportal.backend.persistence.repositories.PasswordResetTokenRepository;
import com.tarique.webportal.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Created by tariquedev on 3/12/17.
 */
@Service
@Transactional(readOnly = true)
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationInMinute;

    /* The Application Logger */
    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetTokenService.class);

    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    /**
     *
     * @param email
     * @return PasswordRestToken
     */
    public PasswordResetToken createPasswordResetTokenForEmail(String email){
        PasswordResetToken passwordResetToken = null;
        User user = userRepository.findByEmail(email);
        if(user!= null){
            String token = UUID.randomUUID().toString();
            LocalDateTime now = LocalDateTime.now();
            passwordResetToken = new PasswordResetToken(token, user, now, tokenExpirationInMinute);
            passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);

            LOG.debug("Successfully created token {} for user {} .", token, user);
        } else {
            LOG.warn("No user found with the email address {} .", email);
        }
        return passwordResetToken;
    }

}
