package com.microreddit.app;

import com.microreddit.app.persistence.models.Privilege;
import com.microreddit.app.persistence.models.Role;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.models.UserKey;
import com.microreddit.app.persistence.repositories.PrivilegeRepository;
import com.microreddit.app.persistence.repositories.RoleRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Inputs default data when the db is wiped or the testing server is restarted. Only run this once, it doesn't
 * do any validation and will just keep loading the same user into the db, breaking the build.
 *
 * @author Josh Harkema.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = true; // change this to false to add test data.
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        // Create privileges.
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        List<Privilege> userPrivileges = Collections.singletonList(readPrivilege);

        // Create roles.
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        // Create initial admin user.
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        UserKey key = new UserKey(UUID.randomUUID(), "admin");
        User user = new User(key);
        user.setPassword(passwordEncoder.encode("ToyCar11"));
        user.setEmail("j@j.com");
        user.setRoles(Collections.singletonList(adminRole.getId()));
        user.setEnabled(true);
        userRepository.insert(user);

        alreadySetup = true;
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);

        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.insert(privilege);
        }

        return privilege;
    }

    private Role createRoleIfNotFound(String name, List<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        List<UUID> pCodes = new ArrayList<>();
        if (role == null) {
            role = new Role(name);
            for (Privilege p : privileges) {
                pCodes.add(p.getId());
            }
            role.setPrivileges(pCodes);
            roleRepository.insert(role);
        }

        return role;
    }

}
