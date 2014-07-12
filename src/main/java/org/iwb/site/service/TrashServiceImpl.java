package org.iwb.site.service;

import org.iwb.site.bo.Trash;
import org.iwb.site.repository.TrashRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@Service
public class TrashServiceImpl implements TrashService {

    @Autowired
    private TrashRepository repository;

    @Override
    public Iterable<Trash> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Trash findTrashById(final Long trashId) {
        return this.repository.findTrashById(trashId);
    }

    @Override
    public Trash save(final Trash trash) {
        return this.repository.save(trash);
    }
}
