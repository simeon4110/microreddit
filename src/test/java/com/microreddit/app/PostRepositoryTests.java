package com.microreddit.app;

import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.repositories.Posts.PostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Josh Harkema
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTests {
    @Autowired
    PostRepository postRepository;

    @Test
    public void readPostsByPageCorrectly() {
        Slice<Post> firstBatch = postRepository.findAll(CassandraPageRequest.first(10));
        Slice<Post> nextBatch = postRepository.findAll(firstBatch.nextPageable());

        Assert.assertEquals(10, firstBatch.getSize());
        Assert.assertEquals(10, nextBatch.getSize());

    }

    @Test
    public void sortBySubAndKarma() {

    }

}
