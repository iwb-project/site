package org.iwb.site;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
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
public class ApplicationConfiguration {

    @Bean
    public Jongo jongo() throws UnknownHostException {
        DB db = new MongoClient("localhost").getDB("iwb-web");
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
