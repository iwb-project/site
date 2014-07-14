package org.iwb.site.service;

import org.iwb.site.bo.Trash;
import org.iwb.site.repository.SecondLifeRepository;
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
    private TrashRepository trashRepository;

    @Autowired
    private SecondLifeRepository secondLifeResources;

    @Override
    public Iterable<Trash> findAll() {
        return this.trashRepository.findAll();
    }

    @Override
    public Trash findTrashById(final Long trashId) {
        return this.trashRepository.findTrashById(trashId);
    }

    @Override
    public Trash save(final Trash trash) {
        return this.trashRepository.save(trash);
    }

    @Override
    public Trash findTrashByLocationAndMaterial(Long locationId, String materialId) {
        Long trashId = this.secondLifeResources.findSecondLifeByLocationAndMaterial(locationId, materialId);
        return this.findTrashById(trashId);
    }
}
