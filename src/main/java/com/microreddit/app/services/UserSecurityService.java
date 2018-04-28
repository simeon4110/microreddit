package com.microreddit.app.services;

import com.microreddit.app.persistence.models.PasswordResetToken;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.repositories.PasswordResetTokenRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

@Service
@ComponentScan(basePackages = {"com.microreddit.app.persistence.repositories"})
public class UserSecurityService {
    private final PasswordResetTokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserSecurityService(PasswordResetTokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public String validatePasswordResetToken(UUID id, String token) {
        final PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if ((resetToken == null) || resetToken.getUser() != id) {
            return "invalidToken";
        }

        final Calendar cal = Calendar.getInstance();
        if ((resetToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "expired";
        }

        final User user = userRepository.findByKey_Id(resetToken.getUser());
        final Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return null;
    }

}
