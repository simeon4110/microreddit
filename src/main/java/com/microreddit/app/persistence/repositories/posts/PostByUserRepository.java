package com.microreddit.app.persistence.repositories.posts;

import com.microreddit.app.persistence.models.Posts.PostByUser;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@Repository
public interface PostByUserRepository extends CassandraRepository<PostByUser, UUID> {
    List<PostByUser> findByKey_UserID(final UUID userID);

    @AllowFiltering
    PostByUser findByKey_PostID(final UUID postID);
}
