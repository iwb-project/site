package org.iwb.site.repository;

import org.iwb.site.bo.SecondLife;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Repository
public class SecondLifeRepositoryImpl implements SecondLifeRepository {

    @Autowired
    @Qualifier("areasCollection")
    private MongoCollection collection;


    @Override
    public Long findSecondLifeByLocationAndMaterial(Long locationId, String materialId) {
        SecondLife sl = this.collection.findOne("{locationId: #, materialId: #}", locationId, materialId).as(SecondLife.class);
        if (sl == null) {
            return null;
        }
        return sl.getTrashId();
    }

    @Override
    public SecondLife save(final SecondLife secondLife) {
        this.collection.save(secondLife);
        return secondLife;
    }
}
