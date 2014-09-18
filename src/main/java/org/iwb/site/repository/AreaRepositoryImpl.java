package org.iwb.site.repository;

import org.iwb.site.bo.Area;
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
public class AreaRepositoryImpl implements AreaRepository {

    @Autowired
    @Qualifier("areasCollection")
    private MongoCollection areasCollection;

    @Override
    public Area findAreaById(String areaId) {
        return this.areasCollection.findOne("_id: {}", areaId).as(Area.class);
    }

    @Override
    public Area save(Area area) {
        this.areasCollection.save(area);
        return area;
    }

    @Override
    public Iterable<Area> findAll() {
        return this.areasCollection.find().as(Area.class);
    }
}
