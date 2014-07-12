package org.iwb.site.transport;

import org.iwb.site.bo.Trash;
import org.iwb.site.service.TrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@RestController
@RequestMapping(value = "/trashes")
public class TrashResources {

    @Autowired
    private TrashService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Trash> listTrashes() {
        return this.service.findAll();
    }

    @RequestMapping(value = "/{trashId}", method = RequestMethod.GET)
    @ResponseBody
    public Trash findTrashById(@PathVariable("trashId") final Long materialId) {
        return this.service.findTrashById(materialId);
    }

}
