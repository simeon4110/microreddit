package com.microreddit.app.services;

import com.microreddit.app.persistence.models.Post;
import com.microreddit.app.persistence.models.PostKey;
import com.microreddit.app.persistence.repositories.PostRepository;
import com.microreddit.app.services.exceptions.SubDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for db operations on Post table.
 *
 * @author Josh Harkema
 */
@Service
public class PostDetailsService {
    private final PostRepository postRepository;

    @Autowired
    public PostDetailsService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createNewPost(final UUID userID, final UUID subID) {
        System.out.println("creating new post...");

        if (postRepository.findByKey_SubID(subID) == null) {
            throw new SubDoesNotExistException("Sub ID: " + subID + " does not exist.");
        }

        PostKey key = new PostKey(userID, subID);
        Post post = new Post(key);
        postRepository.insert(post);

        return post;
    }

    public Post getPostById(UUID id) {
        return postRepository.findByKey_Id(id);
    }

    public List<Post> getPostsByUser(UUID userID) {
        return postRepository.findByKey_UserID(userID);
    }

    public List<Post> getPostsBySub(UUID subID) {
        return postRepository.findByKey_SubID(subID);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

}
