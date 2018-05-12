package com.microreddit.app;

import com.microreddit.app.persistence.models.Posts.Sub.PostBySub;
import com.microreddit.app.persistence.models.Posts.Sub.PostBySubKarma;
import com.microreddit.app.persistence.repositories.posts.PostRepositoryImpl;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubKarmaRepository;
import com.microreddit.app.persistence.repositories.posts.Sub.PostBySubRepository;
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
    @Autowired
    PostBySubKarmaRepository postBySubKarmaRepository;

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
        List<PostBySub> bySub = postBySubRepository.findByKey_SubID(UUID.fromString("4c68fff4-1105-48c9-93e1-1ef1a3795960"));
        for (PostBySub p : bySub) {
            System.out.println(p);
        }

        List<PostBySubKarma> byKarma = postBySubKarmaRepository.findByKey_SubID(
                UUID.fromString("8b13f0c1-50d1-4c2c-9bc7-b4ef436b4ed2")
        );

        for (PostBySubKarma p : byKarma) {
            System.out.println(p);
        }
    }

}
