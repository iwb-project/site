package org.iwb.site.service;

import org.iwb.site.bo.Material;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public interface MaterialService {

    Iterable<Material> findAll();

    Material findItemById(String materialId);

    Material save(Material item);
}
