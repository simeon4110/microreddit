package com.microreddit.app;

import com.microreddit.app.persistence.models.*;
import com.microreddit.app.persistence.repositories.*;
import org.apache.commons.lang3.RandomStringUtils;
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
    private PostRepository postRepository;

    @Autowired
    public InitialDataLoader(UserRepository userRepository, RoleRepository roleRepository,
                             PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder,
                             SubRepository subRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
        this.subRepository = subRepository;
        this.postRepository = postRepository;
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
        subRepository.insert(sub1);
        SubKey subKey2 = new SubKey("test2");
        Sub sub2 = new Sub(subKey2, "test", user.getKey().getId());
        subRepository.insert(sub2);
        SubKey subKey3 = new SubKey("test3");
        Sub sub3 = new Sub(subKey3, "test", user.getKey().getId());
        subRepository.insert(sub3);
        SubKey subKey4 = new SubKey("test4");
        Sub sub4 = new Sub(subKey4, "test", user.getKey().getId());
        subRepository.insert(sub4);
        SubKey subKey5 = new SubKey("test5");
        Sub sub5 = new Sub(subKey5, "test", user.getKey().getId());
        subRepository.insert(sub5);

        // Create initial testing posts.
        generateRandomPosts(user);

        alreadySetup = true;
    }

    private void generateRandomPosts(User user) {
        int max = 250;
        int subCycler = 1;
        Random random = new Random();
        for (int count = 0; count < max; count++) {
            Sub sub = subRepository.findByKey_SubName("test" + subCycler);
            PostKey postKey = new PostKey(user.getKey().getId(), sub.getKey().getId());
            Post post = new Post(postKey);
            post.setSubTitle(sub.getTitle());
            post.setText(RandomStringUtils.randomAscii(256));
            post.setKarma(random.nextInt(1000));
            post.setType("text");
            post.setUsername(user.getKey().getUserName());
            post.setTitle("post" + count);
            postRepository.insert(post);
            user.setPostKarma(user.getPostKarma() + post.getKarma());
            userRepository.save(user);
            if (subCycler == 5) {
                subCycler = 1;
            } else {
                subCycler++;
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
