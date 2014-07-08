package org.iwb.site.repository;

import org.iwb.site.bo.Material;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public interface MaterialRepository {

    Iterable<Material> findAll();

    Material findItemById(String materialId);

    Material save(Material item);

}
