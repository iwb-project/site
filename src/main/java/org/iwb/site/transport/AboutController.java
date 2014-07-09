package org.iwb.site.transport;

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

    /**
     * Returns information about the application.
     *
     * @return see description
     */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public Map<String, Object> about() {
        Map<String, Object> about = new HashMap<>();
        about.put("version", "1.0-SNAPSHOT");
        about.put("buildDate", new Date());
        about.put("gitSha1", "64b786695ecebe7b09a1e19d214ee56fe8ae3263");
        about.put("mongodbVersion", "2.6");
        about.put("elasticsearchVersion", "1.2");
        return about;
    }

}
