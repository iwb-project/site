package org.iwb.site.bo;

import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public class Area {

    private ObjectId id;

    private String name;

    private Iterable<Long> availableTrashes;

    private Iterable<Location> locations;

    private Iterable<SecondLife> secondLives;

    /**
     * Default constructor.
     */
    public Area() {
        // void
    }


    /**
     * Sets new secondLives.
     *
     * @param secondLives New value of secondLives.
     */
    public void setSecondLives(Iterable<SecondLife> secondLives) {
        this.secondLives = secondLives;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Sets new availableTrashes.
     *
     * @param availableTrashes New value of availableTrashes.
     */
    public void setAvailableTrashes(Iterable<Long> availableTrashes) {
        this.availableTrashes = availableTrashes;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets new locations.
     *
     * @param locations New value of locations.
     */
    public void setLocations(Iterable<Location> locations) {
        this.locations = locations;
    }

    /**
     * Gets availableTrashes.
     *
     * @return Value of availableTrashes.
     */
    public Iterable<Long> getAvailableTrashes() {
        return availableTrashes;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets locations.
     *
     * @return Value of locations.
     */
    public Iterable<Location> getLocations() {
        return locations;
    }

    /**
     * Gets secondLives.
     *
     * @return Value of secondLives.
     */
    public Iterable<SecondLife> getSecondLives() {
        return secondLives;
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public ObjectId getId() {
        return id;
    }
}
