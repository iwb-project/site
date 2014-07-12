package org.iwb.site.transport;

import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@RestController
public class AboutController {

    @Autowired
    @Qualifier("itemsCollection")
    private MongoCollection itemsCollections;

    @Value("${project.name}")
    private String name;

    @Value("${project.version}")
    private String version;

    @Value("${project.buildDate}")
    private String buildDate;

    @Value("${project.buildRevision}")
    private String buildRevision;


    /**
     * Returns information about the application.
     *
     * @return see description
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public Map<String, Object> about() {
        Map<String, Object> about = new HashMap<>();
        about.put("name", this.name);
        about.put("version", this.version);
        about.put("buildDate", this.buildDate);
        about.put("gitSha1", this.buildRevision);
        about.put("mongodbVersion", "2.6");
        about.put("elasticsearchVersion", "1.2");
        about.put("itemCount", this.itemsCollections.count());
        return about;
    }

}
