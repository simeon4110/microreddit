package com.microreddit.app.services;

import com.microreddit.app.persistence.dto.UserDto;
import com.microreddit.app.persistence.models.Role;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.models.UserKey;
import com.microreddit.app.persistence.repositories.RoleRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import com.microreddit.app.services.exceptions.EmailAlreadyExistsException;
import com.microreddit.app.services.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Service to manage db connection between views, api, and backend. User CRUD operations should go through here.
 * Everything one can do to a user object, can be done here.
 *
 * @author Josh Harkema
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found.");
        }

        return new AppUserPrincipal(user);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByKey_UserName(username);
    }

    /**
     * @param username user to find.
     * @return a UUID for the user with associated username.
     * @throws UsernameNotFoundException self-explanatory.
     */
    public UUID loadUserIdByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByKey_UserName(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }

            return user.getKey().getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<UUID> getUserSubs(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByKey_UserName(username);
            List<UUID> subs = user.getSubs();
            return subs;
        } catch (UsernameNotFoundException e) {
            System.out.println("Username: " + username + " not found.");
            return null;
        }
    }

    public User registerNewUserAccount(UserDto newUser) throws UsernameAlreadyExistsException {
        System.out.println("registering...");
        if (userRepository.findByKey_UserName(newUser.getUsername()) != null) {
            throw new UsernameAlreadyExistsException(newUser.getUsername() + "already exists.");
        } else if (userRepository.findByEmail(newUser.getEmail()) != null) {
            throw new EmailAlreadyExistsException(newUser.getEmail() + "already exists.");
        } else {
            UserKey key = new UserKey(UUID.randomUUID(), newUser.getUsername());
            User user = new User(key);
            user.setPassword(passwordEncoder.encode(newUser.getPassword()).toLowerCase());
            Role adminRole = roleRepository.findByName("ROLE_ADMIN");
            user.setRoles(Collections.singletonList(adminRole.getId()));
            user.setEmail(newUser.getEmail());
            user.setEnabled(true);
            userRepository.insert(user);

            return user;
        }

    }

}
