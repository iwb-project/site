package org.iwb.site;

import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Application bootstrap.
 *
 * @author mathieu.pousse@zenika.com
 */
public class ApplicationShutdown {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationShutdown.class);

    @Autowired
    private MongoClient mongoClient;

    @PreDestroy
    public void shutdown() {
        LOGGER.debug("application shutdown manager is ready to proceed");
        this.mongoClient.close();
    }

}
