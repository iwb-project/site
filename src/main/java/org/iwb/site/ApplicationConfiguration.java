package org.iwb.site;

import com.mongodb.*;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.iwb.site"})
@ImportResource("classpath:spring-bootstrap.xml")
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    public Jongo jongo() throws UnknownHostException {
        String host = System.getProperty("mongodb.host", "localhost");
        int port = Integer.valueOf(System.getProperty("mongodb.port", "27017"));
        String user = System.getProperty("monngodb.user");
        String pass = System.getProperty("monngodb.pass");
        String database = System.getProperty("monngodb.db", "iwb-dev");

        DB db;
        if (user != null) {
            db = new MongoClient(new ServerAddress(host, port),
                    Arrays.asList(MongoCredential.createPlainCredential(user, database, pass.toCharArray())))
                    .getDB(database);
        } else {
            LOGGER.warn("connecting to mongodb w/o credentials");
            db = new MongoClient(new ServerAddress(host, port)).getDB(database);
        }
        return new Jongo(db);
    }

    @Bean
    public MongoCollection sequencesCollection() throws UnknownHostException {
        return jongo().getCollection("sequences");
    }

    @Bean
    public MongoCollection itemsCollection() throws UnknownHostException {
        return jongo().getCollection("items");
    }

    @Bean
    public MongoCollection materialsCollection() throws UnknownHostException {
        return jongo().getCollection("materials");
    }

}
