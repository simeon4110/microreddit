package com.microreddit.app.persistence.repositories.Posts;

import com.microreddit.app.persistence.models.Posts.Post;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Repo for all post data. Allows query by compound key.
 *
 * @author Josh Harkema
 */
@NoRepositoryBean
public interface PostRepository extends CassandraRepository<Post, UUID> {
    // Post insert (Post post);
}
