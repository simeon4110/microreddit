package com.microreddit.app.services;

import com.microreddit.app.persistence.repositories.UserRepository;
import com.microreddit.app.persistence.repositories.comments.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Josh Harkema
 */
@Service
public class CommentDetailsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentDetailsService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


}
