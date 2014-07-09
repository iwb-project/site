package org.iwb.site.service;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;

import java.util.List;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public interface ItemService {

    Iterable<ItemEssentials> findAll();

    Item findItemById(Long itemId);

    Item save(Item item);
}
