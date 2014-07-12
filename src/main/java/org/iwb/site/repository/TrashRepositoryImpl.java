package org.iwb.site.repository;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;
import org.iwb.site.bo.Trash;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.regex.Pattern;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Repository
public class TrashRepositoryImpl implements TrashRepository {

    @Autowired
    @Qualifier("trashesCollection")
    private MongoCollection collection;

    @Autowired
    private SequenceRepository sequences;


    @Override
    public Iterable<Trash> findAll() {
        return this.collection.find().as(Trash.class);
    }

    @Override
    public Trash findTrashById(final Long trashId) {
        return this.collection.findOne("{_id: #}", trashId).as(Trash.class);
    }

    @Override
    public Trash save(final Trash trash) {
        if (trash.getId() == null) {
            trash.setId(this.sequences.getNextValue("trashes"));
        }
        this.collection.save(trash);
        return trash;
    }
}
