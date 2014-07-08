package org.iwb.site.repository;

import org.iwb.site.bo.Material;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@Repository
public class MaterialRepositoryImpl implements MaterialRepository {

    @Autowired
    @Qualifier("materialsCollection")
    private MongoCollection materialsCollection;

    @Override
    public Material save(final Material material) {
        material.setVersion(1L);
        this.materialsCollection.save(material);
        return material;
    }

    @Override
    public Iterable<Material> findAll() {
        return this.materialsCollection.find().limit(20).as(Material.class);
    }

    @Override
    public Material findItemById(String materialId) {
        return this.materialsCollection.findOne("{_id: #}", materialId).as(Material.class);
    }
}
