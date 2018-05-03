package com.microreddit.app;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySub;
import com.microreddit.app.persistence.repositories.Posts.PostRepositoryImpl;
import com.microreddit.app.persistence.repositories.Posts.Sub.PostBySubRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @author Josh Harkema
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTests {
    @Autowired
    PostRepositoryImpl postRepository;
    @Autowired
    PostBySubRepository postBySubRepository;

//    @Test
//    public void readPostsByPageCorrectly() {
//        Slice<Post> firstBatch = postRepository.findAll(CassandraPageRequest.first(10));
//        Slice<Post> nextBatch = postRepository.findAll(firstBatch.nextPageable());
//
//        Assert.assertEquals(10, firstBatch.getSize());
//        Assert.assertEquals(10, nextBatch.getSize());
//
//    }

    @Test
    public void sortBySubAndScore() {
        List<PostBySub> bySub = postBySubRepository.findByKey_SubID(UUID.fromString("718cdd6f-4c3c-4d0b-af1e-572078ce3e8b"));
        for (PostBySub p : bySub) {
            System.out.println(p);
        }

    }

}
