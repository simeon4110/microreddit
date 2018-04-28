package com.microreddit.app.config;

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
import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Custom Cassandra cluster config.
 *
 * @author Josh Harkema
 */
@Configuration
@EnableCassandraRepositories(basePackages = {"com.microreddit.app.persistence.repositories"})
public class CassandraConfig {
    private static final String KEYSPACE = "db";
    private static final String CONTACTPOINTS = "192.168.0.11";

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(CONTACTPOINTS);
        return cluster;
    }

    @Bean
    public CassandraSessionFactoryBean session() {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(KEYSPACE);
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);

        return session;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        CassandraMappingContext mappingContext = new CassandraMappingContext();
        mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), "db"));
        try {
            mappingContext.setInitialEntitySet(CassandraEntityClassScanner.scan(("com.microreddit.app.persistence.models")));
            mappingContext.setUserTypeResolver(new SimpleUserTypeResolver(cluster().getObject(), KEYSPACE));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}
