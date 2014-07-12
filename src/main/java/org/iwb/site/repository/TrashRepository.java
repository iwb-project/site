package org.iwb.site.repository;

import org.iwb.site.bo.Trash;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public interface TrashRepository {

    Iterable<Trash> findAll();

    Trash findTrashById(Long itemId);

    Trash save(Trash item);

}
