package org.iwb.site.transport;

import org.iwb.site.bo.Material;
import org.iwb.site.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@RestController
@RequestMapping(value = "/materials")
public class MaterialResources {

    @Autowired
    private MaterialService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Material> listMaterials() {
        return this.service.findAll();
    }

    @RequestMapping(value = "/{materialId}", method = RequestMethod.GET)
    @ResponseBody
    public Material findItemById(@PathVariable("materialId") final String materialId) {
        return this.service.findItemById(materialId);
    }

}
