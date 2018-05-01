package com.microreddit.app.Security;

import com.microreddit.app.services.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * Custom Authentication Provider because the default one is shite.
 *
 * @author Josh Harkema
 */
public class CustomAuthentication implements AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthentication(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            UserDetails userToAuth = userDetailsService.loadUserByUsername(user);
            System.out.println(password + ", " + userToAuth.getPassword());
            if (passwordEncoder.matches(password, userToAuth.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
            } else {
                throw new BadCredentialsException("User and password don't match.");
            }
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(user + " not found.");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
