package org.iwb.site.service;

import org.iwb.site.bo.Trash;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public interface TrashService {

    Iterable<Trash> findAll();

    Trash findTrashById(Long trashId);

    Trash save(Trash trash);

    Trash findTrashByLocationAndMaterial(Long locationId, String materialId);
}
