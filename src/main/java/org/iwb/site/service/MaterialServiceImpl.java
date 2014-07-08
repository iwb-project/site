package org.iwb.site.service;

import org.iwb.site.bo.Material;
import org.iwb.site.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepository repository;

    @Override
    public Iterable<Material> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Material findItemById(final String materialId) {
        return this.repository.findItemById(materialId);
    }

    @Override
    public Material save(final Material material) {
        return this.repository.save(material);
    }
}
