package com.microreddit.app.services;

import com.microreddit.app.persistence.dto.UserDto;
import com.microreddit.app.persistence.models.PasswordResetToken;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.models.UserKey;
import com.microreddit.app.persistence.models.VerificationToken;
import com.microreddit.app.persistence.repositories.PasswordResetTokenRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import com.microreddit.app.persistence.repositories.VerificationTokenRepository;
import com.microreddit.app.services.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

/**
 * Service to manage db connection between views, api, and backend. User CRUD operations should go through here.
 * Everything one can do to a user object, can be done here. Most of this is adapted from:
 * https://github.com/Baeldung/spring-security-registration/ and updated to Spring 2.0.
 *
 * @author Josh Harkema
 */
@Service
@ComponentScan(basePackages = {"com.microreddit.app.persistence.repositories"})
public class AppUserDetailsService implements UserDetailsService {
    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
    public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
    public static String APP_NAME = "SpringRegistration";
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository,
                                 PasswordResetTokenRepository passwordResetTokenRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new AppUserPrincipal(userRepository.findByKey_UserName(s));
    }

    public User getUser(final String verificationToken) {
        final VerificationToken token = verificationTokenRepository.findByToken(verificationToken);
        if (token != null) {
            return userRepository.findByKey_Id(token.getUserID());
        }

        return null;
    }

    public VerificationToken getVerificationToken(final String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    public void deleteUser(final User user) {
        final VerificationToken verificationToken = verificationTokenRepository.findByUserID(user.getKey().getId());
        final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUser(user.getKey().getId());

        if (verificationToken != null) {
            verificationTokenRepository.delete(verificationToken);
        }

        if (passwordResetToken != null) {
            passwordResetTokenRepository.delete(passwordResetToken);
        }
        userRepository.delete(user);
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.insert(verificationToken);
    }

    public VerificationToken generateNewVerificationToken(final String existingToken) {
        VerificationToken token = verificationTokenRepository.findByToken(existingToken);
        token.updateToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(token);

        return token;
    }

    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user.getKey().getId());
        passwordResetTokenRepository.save(myToken);
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    public User getUserByPasswordResetToken(final String token) {
        return userRepository.findByKey_Id(passwordResetTokenRepository.findByToken(token).getUser());
    }

    public User getUserByID(final UUID id) {
        return userRepository.findByKey_Id(id);
    }

    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {

            return TOKEN_INVALID;
        }

        final User user = userRepository.findByKey_Id(verificationToken.getUserID());
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(verificationToken);

            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // verificationTokenRepository.delete(verificationToken);
        userRepository.save(user);

        return TOKEN_VALID;
    }

    public User registerNewUserAccount(UserDto newUser) throws UsernameAlreadyExistsException {
        System.out.println("registering...");
        if (userRepository.findByKey_UserName(newUser.getUsername()) != null) {
            throw new UsernameAlreadyExistsException(newUser.getUsername() + "already exists.");
        } else {
            UserKey key = new UserKey(UUID.randomUUID(), newUser.getUsername());
            User user = new User(key);
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setEmail(newUser.getEmail());
            userRepository.insert(user);

            return user;
        }
    }

}
