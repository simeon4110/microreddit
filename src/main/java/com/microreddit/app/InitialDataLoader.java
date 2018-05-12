package com.microreddit.app;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.models.*;
import com.microreddit.app.persistence.repositories.PrivilegeRepository;
import com.microreddit.app.persistence.repositories.RoleRepository;
import com.microreddit.app.persistence.repositories.SubRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import com.microreddit.app.persistence.repositories.posts.PostRepositoryImpl;
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
    private SubRepository subRepository;
    private PostRepositoryImpl postRepository;
    private List<Sub> subs;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder,
                             SubRepository subRepository, PostRepositoryImpl postRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.subRepository = subRepository;
        this.postRepository = postRepository;
        this.subs = new ArrayList<>();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        userRepository.deleteAll();
        roleRepository.deleteAll();
        privilegeRepository.deleteAll();
        subRepository.deleteAll();
        postRepository.deleteAll();

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

        // Create initial testing subs.
        SubKey subKey1 = new SubKey("test1");
        Sub sub1 = new Sub(subKey1, "test", user.getKey().getId());
        subs.add(sub1);
        subRepository.insert(sub1);
        SubKey subKey2 = new SubKey("test2");
        Sub sub2 = new Sub(subKey2, "test", user.getKey().getId());
        subs.add(sub2);
        subRepository.insert(sub2);
        SubKey subKey3 = new SubKey("test3");
        Sub sub3 = new Sub(subKey3, "test", user.getKey().getId());
        subs.add(sub3);
        subRepository.insert(sub3);
        SubKey subKey4 = new SubKey("test4");
        Sub sub4 = new Sub(subKey4, "test", user.getKey().getId());
        subs.add(sub4);
        subRepository.insert(sub4);
        SubKey subKey5 = new SubKey("test5");
        Sub sub5 = new Sub(subKey5, "test", user.getKey().getId());
        subs.add(sub5);
        subRepository.insert(sub5);

        generateRandomPosts(user);

        alreadySetup = true;
    }

    private void generateRandomPosts(User user) {
        int max = 50;
        int cycler = 0;
        Random r = new Random();

        for (int i = 0; i < max; i++) {
            Sub sub = subs.get(cycler);
            Post post = new Post();
            post.setUserID(user.getKey().getId());
            post.setUsername(user.getKey().getUserName());
            post.setSubID(sub.getKey().getId());
            post.setSubName(sub.getTitle());
            post.setScore(r.nextInt(250));
            post.setKarma(r.nextInt(500));
            post.setTitle("Post " + i);
            post.setText("Post text...");
            post.setType("LINK");

            postRepository.insert(post);

            if (cycler == 4) {
                cycler = 0;
            } else {
                cycler++;
            }
        }

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
