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

@Service
public class SubDetailsServiceImpl {
    private UserRepository userRepository;
    private SubRepository subRepository;

    @Autowired
    public SubDetailsServiceImpl(UserRepository userRepository, SubRepository subRepository) {
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
        } else {
            SubKey subKey = new SubKey(name);
            Sub sub = new Sub(subKey, description, creatorID);
            subRepository.insert(sub);

            User user = userRepository.findByKey_Id(creatorID);
            user.addSub(sub.getKey().getId());
            userRepository.save(user);

            return sub;
        }

    }

    public void save(Sub sub) {
        subRepository.save(sub);
    }

}
