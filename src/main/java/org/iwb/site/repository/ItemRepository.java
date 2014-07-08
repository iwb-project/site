package org.iwb.site.repository;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public interface ItemRepository {

    Iterable<ItemEssentials> findAll();

    Item findItemById(String itemId);

    Item save(Item item);
}
