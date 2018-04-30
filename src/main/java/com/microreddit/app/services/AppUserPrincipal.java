package com.microreddit.app.services;

import com.microreddit.app.persistence.models.Privilege;
import com.microreddit.app.persistence.models.Role;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.repositories.PrivilegeRepository;
import com.microreddit.app.persistence.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * POJO intermediary for user registration and authentication.
 *
 * @author Josh Harkema
 */
public class AppUserPrincipal implements UserDetails {
    private User user;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;

    public AppUserPrincipal(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        for (final UUID role : user.getRoles()) {
            Optional<Role> found = roleRepository.findById(role);
            if (found.isPresent()) {
                Role r = found.get();
                List<UUID> privileges = r.getPrivileges();
                for (UUID p : privileges) {
                    Optional<Privilege> foundp = privilegeRepository.findById(p);
                    if (foundp.isPresent()) {
                        Privilege privilege = foundp.get();
                        authorities.add(new SimpleGrantedAuthority(privilege.getName()));
                    }
                }
            }
        }

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
