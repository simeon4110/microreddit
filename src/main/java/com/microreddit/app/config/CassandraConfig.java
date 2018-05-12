package com.microreddit.app.config;

import com.microreddit.app.persistence.models.Comments.Comment;
import com.microreddit.app.persistence.models.Posts.Post;
import com.microreddit.app.persistence.repositories.comments.CommentByPostRepository;
import com.microreddit.app.persistence.repositories.comments.CommentByUserRepository;
import com.microreddit.app.persistence.repositories.comments.CommentRepository;
import com.microreddit.app.persistence.repositories.comments.CommentRepositoryImpl;
import com.microreddit.app.persistence.repositories.posts.PostRepository;
import com.microreddit.app.persistence.repositories.posts.PostRepositoryImpl;
import com.microreddit.app.persistence.repositories.posts.Sub.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraEntityClassScanner;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

import java.util.UUID;

/**
 * Custom Cassandra cluster config.
 *
 * @author Josh Harkema
 */
@Configuration
//@PropertySource("classpath:environment.properties")
@EnableCassandraRepositories(basePackages = {"com.microreddit.app.persistence.repositories"})
public class CassandraConfig {
    //    @Value("${cassandra.keyspace}")
    private static String KEYSPACE = "db";
    //    @Value("${cassandra.contactpoints}")
    private static String CONTACTPOINTS = "192.168.0.11";

    private String getKeyspaceName() {
        return KEYSPACE;
    }

    @Bean
    public CassandraClusterFactoryBean cluster() {
        final CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(CONTACTPOINTS);
        return cluster;
    }


    @Bean
    public CassandraMappingContext mappingContext() throws ClassNotFoundException {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setInitialEntitySet(CassandraEntityClassScanner.scan("com.microreddit.app.persistence.models"));
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), getKeyspaceName()));
        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() throws ClassNotFoundException {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(getKeyspaceName());
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
        return session;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }

    // This is where PostRepositoryImpl gets its config.
    @Bean
    public PostRepository postRepository(final CassandraTemplate template,
                                         final PostBySubRepository postBySubRepository,
                                         final PostBySubCommentsRepository postBySubCommentsRepository,
                                         final PostBySubKarmaRepository postBySubKarmaRepository,
                                         final PostBySubNewRepository postBySubNewRepository,
                                         final PostBySubScoreRepository postByScoreRepository) throws Exception {
        final CassandraPersistentEntity<?> entity = template.getConverter().getMappingContext()
                .getRequiredPersistentEntity(Post.class);
        final MappingCassandraEntityInformation metadata = new MappingCassandraEntityInformation<Post, UUID>(
                (CassandraPersistentEntity<Post>) entity, template.getConverter()
        );

        return new PostRepositoryImpl(metadata, template, postBySubRepository, postBySubCommentsRepository,
                postBySubKarmaRepository, postBySubNewRepository, postByScoreRepository);
    }

    // Comment repository conf.
    @Bean
    public CommentRepository commentRepository(final CassandraTemplate template,
                                               final CommentByPostRepository commentByPostRepository,
                                               final CommentByUserRepository commentByUserRepository) throws Exception {
        final CassandraPersistentEntity<?> commentEntity = template.getConverter().getMappingContext()
                .getRequiredPersistentEntity(Comment.class);
        final MappingCassandraEntityInformation commentMetadata = new MappingCassandraEntityInformation<Comment, UUID>(
                (CassandraPersistentEntity<Comment>) commentEntity, template.getConverter()
        );

        return new CommentRepositoryImpl(commentMetadata, template, commentByPostRepository, commentByUserRepository);
    }

}
