package org.iwb.site.transport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iwb.site.bo.Item;
import org.iwb.site.bo.Material;
import org.iwb.site.repository.MaterialRepository;
import org.iwb.site.service.ItemService;
import org.jongo.Jongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@RestController
@RequestMapping("/bootstrap")
public class BootstrapDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapDataController.class);

    @Autowired
    private ItemService itemService;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private Jongo db;

    @RequestMapping("/db")
    public Map<String, Boolean> bootstrapDB(@RequestParam(value = "drop", defaultValue = "false") boolean drop) {

        Map<String, Boolean> result = new HashMap<>();
        if (drop) {
            LOGGER.debug("dropping database");
            this.db.getCollection("materials").drop();
            this.db.getCollection("items").drop();
            this.db.getCollection("sequences").drop();
        }

        result.put("loadMaterials", loadMaterials());
        result.put("loadItems", loadItems());

        return result;
    }

    private boolean loadItems() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Item> items = mapper.readValue(BootstrapDataController.class.getResourceAsStream("/bootstrap/items.json"), new TypeReference<List<Item>>() {});
            for (Item item : items) {
                this.itemService.save(item);
            }
        } catch (IOException e) {
            LOGGER.error("oops", e);
            return false;
        }
        return true;
    }


    private boolean loadMaterials() {
        this.materialRepository.save(new Material("NON-RECYCLABLE", "Déchet non recyclable"));
        this.materialRepository.save(new Material("COMPOST", "Déchet végétal"));
        this.materialRepository.save(new Material("PLASTIC", "Plastique recyclable"));
        this.materialRepository.save(new Material("TOXIC", "Déchet toxic necessitant un traitement particulier"));
        this.materialRepository.save(new Material("MEDICAL", "Déchet médical"));
        this.materialRepository.save(new Material("GLASS", "Verre"));
        this.materialRepository.save(new Material("TEXTILE", "Déchet textile"));
        this.materialRepository.save(new Material("PAPER", "Papier"));
        this.materialRepository.save(new Material("CARTON", "Carton"));
        this.materialRepository.save(new Material("ELECTRONIC", "Déchet electronique"));
        this.materialRepository.save(new Material("FURNITURE", "Déchet végétaux"));
        this.materialRepository.save(new Material("METAL", "Métaux"));
        this.materialRepository.save(new Material("WOOD", "Bois"));

        return true;
    }

}

