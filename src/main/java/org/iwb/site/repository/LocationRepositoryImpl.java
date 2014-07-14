package org.iwb.site.repository;

import org.iwb.site.bo.Location;
import org.iwb.site.bo.Trash;
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
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    @Qualifier("locationsCollection")
    private MongoCollection collection;

    @Autowired
    private SequenceRepository sequences;


    @Override
    public Iterable<Location> findAll() {
        return this.collection.find().as(Location.class);
    }

    @Override
    public Location findLocationById(final Long locationId) {
        return this.collection.findOne("{_id: #}", locationId).as(Location.class);
    }

    @Override
    public Location save(final Location location) {
        this.collection.save(location);
        return location;
    }
}
