package org.iwb.site;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.iwb.site"})
@ImportResource("classpath:spring-bootstrap.xml")
@PropertySource("classpath:version.properties")
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ApplicationShutdown shutdown() {
        return new ApplicationShutdown();
    }

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        String host = System.getProperty("mongodb.host", "localhost");
        int port = Integer.valueOf(System.getProperty("mongodb.port", "27017"));
        String user = System.getProperty("mongodb.user");
        String pass = System.getProperty("mongodb.pass", "");
        String database = System.getProperty("mongodb.db", "iwb-dev");

        LOGGER.info("connecting to mongodb://{}:{}@{}:{}/{}", user, pass.replaceAll(".", "*"), host, port, database);
        if (user != null) {
            MongoClientURI uri = new MongoClientURI(String.format("mongodb://%s:%s@%s:%d/%s", user, pass, host, port, database));
            return new MongoClient(uri);
        } else {
            LOGGER.warn("connecting to mongodb w/o credentials");
            return new MongoClient(new ServerAddress(host, port));
        }
    }

    public DB db() throws UnknownHostException {
        String database = System.getProperty("mongodb.db", "iwb-dev");
        return mongoClient().getDB(database);
    }

    @Bean
    public Jongo jongo() throws UnknownHostException {
        return new Jongo(db());
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

    @Bean
    public MongoCollection trashesCollection() throws UnknownHostException {
        return jongo().getCollection("trashes");
    }

    @Bean
    public MongoCollection locationsCollection() throws UnknownHostException {
        return jongo().getCollection("locations");
    }

    @Bean
    public MongoCollection secondLivesCollection() throws UnknownHostException {
        return jongo().getCollection("secondLives");
    }

}
