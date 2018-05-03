package com.microreddit.app.persistence.repositories.Posts;

import com.microreddit.app.persistence.models.Posts.Post;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Empty for a reason.
 *
 * @author Josh Harkema
 */
@NoRepositoryBean
public interface PostRepository extends CassandraRepository<Post, UUID> {
    //
}
