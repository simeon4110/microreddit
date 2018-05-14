package com.microreddit.app.services;

import com.microreddit.app.persistence.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * POJO intermediary for user registration and authentication.
 *
 * @author Josh Harkema
 */
public class AppUserPrincipal implements UserDetails, Serializable {
    private final User user;

    public AppUserPrincipal(User user) {
        this.user = user;
    }

    /**
     * This is a mess because spring data is a disaster if your db doesn't store UDT's well.
     *
     * @return a collection of the user's authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getKey().getUserName();
    }

    public int getPostKarma() {
        return user.getPostKarma();
    }

    public int getCommentKarma() {
        return user.getCommentKarma();
    }

    public UUID getID() {
        return user.getKey().getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
