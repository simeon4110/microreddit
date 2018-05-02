package com.microreddit.app.services;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.repositories.Posts.PostRepository;
import com.microreddit.app.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service for db operations on Post table.
 *
 * @author Josh Harkema
 */
@Service
@CacheConfig(cacheNames = "post-cache")
public class PostDetailsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostDetailsService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createNewPost(final UUID userID, final UUID subID) {
        System.out.println("creating new post...");

        Post post = new Post();
        post.setUserID(userID);
        post.setUsername(userRepository.findByKey_Id(userID).getKey().getUserName());
        post.setSubID(subID);
        postRepository.insert(post);

        return post;
    }

    @Cacheable(value = "all")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

}
