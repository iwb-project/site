package org.iwb.site.service;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;
import org.iwb.site.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Iterable<ItemEssentials> findAll() {
        return this.itemRepository.findAll();
    }

    @Override
    public Iterable<ItemEssentials> search(final String query) {
        if (StringUtils.isEmpty(query)) {
            return findAll();
        } else {
            return this.itemRepository.search(query);
        }
    }

    @Override
    public Item findItemById(final Long itemId) {
        return this.itemRepository.findItemById(itemId);
    }

    @Override
    public Item save(final Item item) {
        return this.itemRepository.save(item);
    }
}
