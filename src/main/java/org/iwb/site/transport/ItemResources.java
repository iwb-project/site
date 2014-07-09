package org.iwb.site.transport;

import org.iwb.site.bo.Item;
import org.iwb.site.bo.ItemEssentials;
import org.iwb.site.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
@RestController
@RequestMapping(value = "/items")
public class ItemResources {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemResources.class);

    @Autowired
    private ItemService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ItemEssentials> listItems() {
        return this.service.findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ItemEssentials> searchItems(@RequestParam("q") String query) {
        LOGGER.debug("searching for {}", query);
        return this.service.search(query);
    }


    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public Item findItemById(@PathVariable("itemId") final Long itemId) {
        return this.service.findItemById(itemId);
    }

}
