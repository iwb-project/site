package org.iwb.site.repository;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

import java.util.regex.Pattern;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    @Qualifier("itemsCollection")
    private MongoCollection collection;

    @Autowired
    private SequenceRepository sequences;


    @Override
    public Iterable<ItemEssentials> findAll() {
        return this.collection.find().limit(20).as(ItemEssentials.class);
    }

    @Override
    public Item findItemById(final Long itemId) {
        return this.collection.findOne("{_id: #}", itemId).as(Item.class);
    }

    @Override
    public Item save(final Item item) {
        if (item.getId() == null) {
            item.setId(this.sequences.getNextValue("items"));
        }
        this.collection.save(item);
        return item;
    }

    @Override
    public Iterable<ItemEssentials> search(String query) {
        Pattern regex = Pattern.compile(".*" + query + ".*", Pattern.CASE_INSENSITIVE);
        return collection.find("{$or: [{name: #}, {barcode: #}]}", regex, query).as(ItemEssentials.class);
    }
}
