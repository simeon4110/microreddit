package com.microreddit.app.services;

import com.microreddit.app.persistence.models.Sub;
import com.microreddit.app.persistence.models.SubKey;
import com.microreddit.app.persistence.models.User;
import com.microreddit.app.persistence.repositories.SubRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import com.microreddit.app.services.exceptions.SubAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for db operations on the Sub table.
 *
 * @author Josh Harkema
 */
@Service
public class SubDetailsService {
    private final UserRepository userRepository;
    private final SubRepository subRepository;

    @Autowired
    public SubDetailsService(UserRepository userRepository, SubRepository subRepository) {
        this.userRepository = userRepository;
        this.subRepository = subRepository;
    }

    public Sub getSubById(UUID id) {
        return subRepository.findByKey_Id(id);
    }

    public Sub getSubByName(String name) {
        return subRepository.findByKey_SubName(name);
    }

    public Sub createNewSub(String name, String description, UUID creatorID) {
        System.out.println("creating new sub...");

        if (subRepository.findByKey_SubName(name) != null) {
            throw new SubAlreadyExistsException(name + " already exists.");
        }

        SubKey subKey = new SubKey(name);
        Sub sub = new Sub(subKey, description, creatorID);
        subRepository.insert(sub);

        User user = userRepository.findByKey_Id(creatorID);
        user.addSub(sub.getKey().getId());
        userRepository.save(user);

        return sub;
    }

    public void save(Sub sub) {
        subRepository.save(sub);
    }

}
