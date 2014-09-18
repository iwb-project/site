package org.iwb.site.transport;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.iwb.site.bo.*;
import org.iwb.site.repository.LocationRepository;
import org.iwb.site.repository.MaterialRepository;
import org.iwb.site.repository.SecondLifeRepository;
import org.iwb.site.repository.TrashRepository;
import org.iwb.site.service.AreaService;
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
import java.util.*;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@RestController
@RequestMapping("/bootstrap")
public class BootstrapDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapDataController.class);

    public static final String NON_RECYCLABLE = "NON-RECYCLABLE";
    public static final String COMPOST = "COMPOST";
    public static final String PLASTIC = "PLASTIC";
    public static final String TOXIC = "TOXIC";
    public static final String MEDICAL = "MEDICAL";
    public static final String GLASS = "GLASS";
    public static final String TEXTILE = "TEXTILE";
    public static final String PAPER = "PAPER";
    public static final String CARTON = "CARTON";
    public static final String ELECTRONIC = "ELECTRONIC";
    public static final String FURNITURE = "FURNITURE";
    public static final String METAL = "METAL";
    public static final String WOOD = "WOOD";

    @Autowired
    private ItemService itemService;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TrashRepository trashRepository;

    @Autowired
    private AreaService areaService;


    @Autowired
    private Jongo db;

    @Autowired
    private ApplicationContext context;

    private Location rennes;

    private Trash trashYellow;
    private Trash compost;
    private Trash specialCompost;
    private Trash notRecyclable;
    private Trash specialPaper;
    private Trash verre;
    private Trash pharmacie;
    private Trash déchetterie;
    private Trash incénration;
    private Trash glasses;

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
        result.put("loadAreas", loadAreas());

        return result;
    }

    private boolean loadAreas() {
        Area noRecylingArea = new Area();
        noRecylingArea.setName("noRecyclingArea");
        noRecylingArea.setSecondLives(noRecycling());
        noRecylingArea.setAvailableTrashes(getTrashInUse(noRecylingArea.getSecondLives()));
        noRecylingArea.setLocations(Arrays.asList(new Location(35000, "Rennes")));
        this.areaService.save(noRecylingArea);

        Area basicRecycling = new Area();
        basicRecycling.setName("basicRecyclingArea");
        basicRecycling.setSecondLives(basicRecycling());
        basicRecycling.setAvailableTrashes(getTrashInUse(basicRecycling.getSecondLives()));
        basicRecycling.setLocations(Arrays.asList(new Location(51100, "Reims")));
        this.areaService.save(basicRecycling);

        Area ultimateRecycling = new Area();
        ultimateRecycling.setName("ultimateRecyclingArea");
        ultimateRecycling.setSecondLives(ultimateRecycling());
        ultimateRecycling.setAvailableTrashes(getTrashInUse(ultimateRecycling.getSecondLives()));
        ultimateRecycling.setLocations(Arrays.asList(new Location(51100, "Reims")));
        this.areaService.save(basicRecycling);

        return true;
    }

    private Iterable<Long> getTrashInUse(Iterable<SecondLife> secondLives) {
        Set<Long> inUse = new HashSet<>();
        for (SecondLife secondLife : secondLives) {
            inUse.add(secondLife.getTrashId());
        }
        return inUse;
    }

    private List<SecondLife> noRecycling() {
        List<SecondLife> secondLives = new ArrayList<>();
        for (Material material : materials()) {
            secondLives.add(new SecondLife(material.getConstant(), this.notRecyclable.getId()));
        }
        return secondLives;
    }

    private List<SecondLife> basicRecycling() {
        return Arrays.asList(
                new SecondLife(NON_RECYCLABLE, this.notRecyclable.getId()),
                new SecondLife(COMPOST, this.compost.getId()),
                new SecondLife(PLASTIC, this.trashYellow.getId()),
                new SecondLife(TOXIC, this.déchetterie.getId()),
                new SecondLife(MEDICAL, this.pharmacie.getId()),
                new SecondLife(GLASS, this.glasses.getId()),
                new SecondLife(TEXTILE, this.notRecyclable.getId()),
                new SecondLife(PAPER, this.trashYellow.getId()),
                new SecondLife(CARTON, this.déchetterie.getId()),
                new SecondLife(ELECTRONIC, this.déchetterie.getId()),
                new SecondLife(FURNITURE, this.déchetterie.getId()),
                new SecondLife(METAL, this.déchetterie.getId()),
                new SecondLife(WOOD, this.déchetterie.getId())
        );
    }

    private List<SecondLife> ultimateRecycling() {
        return Arrays.asList(
                new SecondLife(NON_RECYCLABLE, this.notRecyclable.getId()),
                new SecondLife(COMPOST, this.specialCompost.getId()),
                new SecondLife(PLASTIC, this.déchetterie.getId()),
                new SecondLife(TOXIC, this.déchetterie.getId()),
                new SecondLife(MEDICAL, this.pharmacie.getId()),
                new SecondLife(GLASS, this.glasses.getId()),
                new SecondLife(TEXTILE, this.notRecyclable.getId()),
                new SecondLife(PAPER, this.specialPaper.getId()),
                new SecondLife(CARTON, this.déchetterie.getId()),
                new SecondLife(ELECTRONIC, this.déchetterie.getId()),
                new SecondLife(FURNITURE, this.déchetterie.getId()),
                new SecondLife(METAL, this.déchetterie.getId()),
                new SecondLife(WOOD, this.déchetterie.getId())
        );
    }

    private boolean loadTrashes() {
        this.trashYellow = this.trashRepository.save(new Trash("Recyclable jaune", "glyphicon glyphicon-trash trash-yellow"));
        this.specialCompost = this.trashRepository.save(new Trash("Composte collecté", "glyphicon glyphicon-trash trash-green"));
        this.notRecyclable = this.trashRepository.save(new Trash("Ordure ménagère", "glyphicon glyphicon-trash trash-gray"));
        this.specialPaper = this.trashRepository.save(new Trash("Papier", "glyphicon glyphicon-trash trash-brawn"));
        this.compost = this.trashRepository.save(new Trash("Composte", "glyphicon glyphicon-leaf trash-compost"));
        this.verre = this.trashRepository.save(new Trash("Verre", "glyphicon glyphicon-download-alt trash-glass"));
        this.pharmacie = this.trashRepository.save(new Trash("Pharmacie", "glyphicon glyphicon-plus-sign my-green"));
        this.déchetterie = this.trashRepository.save(new Trash("Déchetterie", "glyphicon glyphicon-new-window my-green"));
        this.incénration = this.trashRepository.save(new Trash("Incénration", "glyphicon glyphicon-fire my-yellow"));
        this.glasses = this.trashRepository.save(new Trash("Bac à verre", "glyphicon glyphicon-edit my-blue"));
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
        for (Material material : materials()) {
            this.materialRepository.save(material);
        }
        return true;
    }

    private List<Material> materials() {
        return Arrays.asList(new Material(NON_RECYCLABLE, "Déchet non recyclable"),
                new Material(COMPOST, "Déchet végétal"),
                new Material(PLASTIC, "Plastique recyclable"),
                new Material(TOXIC, "Déchet toxic"),
                new Material(MEDICAL, "Déchet médical"),
                new Material(GLASS, "Verre"),
                new Material(TEXTILE, "Déchet textile"),
                new Material(PAPER, "Papier"),
                new Material(CARTON, "Carton"),
                new Material(ELECTRONIC, "Déchet electronique"),
                new Material(FURNITURE, "Déchet végétaux"),
                new Material(METAL, "Métaux"),
                new Material(WOOD, "Bois"));
    }

}

