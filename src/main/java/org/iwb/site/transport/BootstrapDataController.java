package org.iwb.site.transport;

import com.mongodb.MongoClient;
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
    public String bootstrapDB(@RequestParam(value = "drop", defaultValue = "false") boolean drop) {
        if (drop) {
            LOGGER.debug("dropping database");
            this.db.getCollection("materials").drop();
            this.db.getCollection("items").drop();
            this.db.getCollection("sequences").drop();
        }


        loadMaterials();
        loadItems();

        return "ok";
    }

    private void loadItems() {
        Item water = new Item();
        water.setName("Bouteille d'eau minérale naturelle des Alpes");
        water.setBarcode("3560070564743");

        this.itemService.save(water);
    }


    private void loadMaterials() {
        this.materialRepository.save(new Material("non-recycable", "Déchet non recyclable"));
        this.materialRepository.save(new Material("compost", "Déchet végétal"));
        this.materialRepository.save(new Material("plastic", "Plastique recyclable"));
        this.materialRepository.save(new Material("toxic", "Déchet toxic necessitant un traitement particulier"));
        this.materialRepository.save(new Material("medical", "Déchet médical"));
        this.materialRepository.save(new Material("glass", "Verre"));
        this.materialRepository.save(new Material("textile", "Déchet textile"));
        this.materialRepository.save(new Material("paper", "Papier"));
        this.materialRepository.save(new Material("carton", "Carton"));
        this.materialRepository.save(new Material("electronic", "Déchet electronique"));
        this.materialRepository.save(new Material("furniture", "Déchet végétaux"));
        this.materialRepository.save(new Material("metal", "Métaux"));
        this.materialRepository.save(new Material("wood", "Bois"));
    }

}

