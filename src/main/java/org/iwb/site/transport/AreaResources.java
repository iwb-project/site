package org.iwb.site.transport;

import org.iwb.site.bo.Area;
import org.iwb.site.bo.SecondLife;
import org.iwb.site.bo.Trash;
import org.iwb.site.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@RestController
@RequestMapping(value = "/areas")
public class AreaResources {

    @Autowired
    private AreaService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Iterable<Area> listAreas() {
        return this.service.findAll();
    }

    @RequestMapping(value = "/{areaId}", method = RequestMethod.GET)
    public Area findLocationById(@PathVariable("areaId") final String areaId) {
        return this.service.findById(areaId);
    }

    @RequestMapping(value = "/{areaId}/trashes/", method = RequestMethod.GET)
    public Iterable<Trash> findTrashesForLocationById(@PathVariable("areaId") final String areaId) {
        return this.service.findById(areaId).getAvailableTrashes();
    }

    @RequestMapping(value = "/{areaId}/second-lives/", method = RequestMethod.GET)
    public Iterable<SecondLife> findSecondLivesForLocationById(@PathVariable("areaId") final String areaId) {
        return this.service.findById(areaId).getSecondLives();
    }

}
