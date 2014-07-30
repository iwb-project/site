package org.iwb.site.transport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iwb.site.bo.*;
import org.iwb.site.repository.LocationRepository;
import org.iwb.site.repository.MaterialRepository;
import org.iwb.site.repository.SecondLifeRepository;
import org.iwb.site.repository.TrashRepository;
import org.iwb.site.service.ItemService;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private TrashRepository trashRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SecondLifeRepository secondLifeRepository;

    @Autowired
    private Jongo db;

    @Autowired
    private ApplicationContext context;

    private Location rennes;

    private Trash trashYellow;
    private Trash trashGray;
    private Trash compost;

    @RequestMapping("/db")
    public Map<String, Boolean> bootstrapDB(@RequestParam(value = "drop", defaultValue = "false") boolean drop) {

        Map<String, Boolean> result = new HashMap<>();
        if (drop) {
            LOGGER.warn("dropping database");
            this.context.getBean("locationsCollection", MongoCollection.class).drop();
            this.context.getBean("materialsCollection", MongoCollection.class).drop();
            this.context.getBean("itemsCollection", MongoCollection.class).drop();
            this.context.getBean("trashesCollection", MongoCollection.class).drop();
            this.context.getBean("secondLivesCollection", MongoCollection.class).drop();
            this.context.getBean("sequencesCollection", MongoCollection.class).drop();
        }

        result.put("loadMaterials", loadMaterials());
        result.put("loadItems", loadItems());
        result.put("loadTrashes", loadTrashes());
        result.put("loadLocations", loadLocations());
        result.put("loadSecondLives", loadSecondLives());

        return result;
    }

    private boolean loadSecondLives() {
        this.secondLifeRepository.save(new SecondLife(35000L, "COMPOST", this.compost.getId()));
        this.secondLifeRepository.save(new SecondLife(35000L, "PLASTIC", this.trashYellow.getId()));
        this.secondLifeRepository.save(new SecondLife(35000L, "PAPER", this.trashYellow.getId()));
        this.secondLifeRepository.save(new SecondLife(35000L, "GLASS", this.trashYellow.getId()));
        this.secondLifeRepository.save(new SecondLife(35000L, "METAL", this.trashYellow.getId()));
        this.secondLifeRepository.save(new SecondLife(35000L, "NON-RECYCLABLE", this.trashGray.getId()));
        return true;
    }

    private boolean loadLocations() {
        this.rennes = this.locationRepository.save(new Location(35000, "Rennes"));
        this.locationRepository.save(new Location(35190, "Tinténiac"));
        this.locationRepository.save(new Location(59000, "Lille"));
        return true;
    }

    private boolean loadTrashes() {
        this.trashYellow = this.trashRepository.save(new Trash("glyphicon glyphicon-trash trash-yellow"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-trash trash-green"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-trash trash-black"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-trash trash-brawn"));
        this.trashGray = this.trashRepository.save(new Trash("glyphicon glyphicon-trash trash-gray"));
        this.compost = this.trashRepository.save(new Trash("glyphicon glyphicon-leaf trash-compost"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-download-alt trash-glass"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-download-alt trash-black"));
        this.trashRepository.save(new Trash("glyphicon glyphicon-fire trash-yellow"));
        return true;
    }

    private boolean loadItems() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Item> items = mapper.readValue(BootstrapDataController.class.getResourceAsStream("/bootstrap/items.json"), new TypeReference<List<Item>>() {
            });
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
        this.materialRepository.save(new Material("TOXIC", "Déchet toxic"));
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

