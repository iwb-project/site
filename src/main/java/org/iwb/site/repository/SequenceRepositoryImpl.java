package org.iwb.site.repository;

import org.iwb.site.bo.Sequence;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicLong;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@Repository
public class SequenceRepositoryImpl implements SequenceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequenceRepositoryImpl.class);

    private AtomicLong holder = new AtomicLong(0L);

    @Autowired
    @Qualifier("sequencesCollection")
    private MongoCollection sequences;

    @Override
    public Long getNextValue(final String sequence) {

//        Sequence s = this.sequences.findAndModify("{_id: #}", sequence)
//                .upsert()
//                .with("{$setOnInsert: {'$inc': 'counter'}}")
//                .as(Sequence.class);

        Long nextVal = holder.incrementAndGet();

        LOGGER.debug("sequence.{}.nextval is {}", sequence, nextVal);

        return nextVal;
    }
}
