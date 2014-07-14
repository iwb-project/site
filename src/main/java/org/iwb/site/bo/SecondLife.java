package org.iwb.site.bo;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class SecondLife {

    @Id
    private String _id;

    private Long locationId;

    private Long trashId;

    private String materialId;

    /**
     * Default constructor.
     */
    public SecondLife() {
        // void
    }

    public SecondLife(Long locationId, String materialId, Long trashId) {
        this.locationId = locationId;
        this.materialId = materialId;
        this.trashId = trashId;
    }

    /**
     * Gets materialId.
     *
     * @return Value of materialId.
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * Sets new materialId.
     *
     * @param materialId New value of materialId.
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * Sets new locationId.
     *
     * @param locationId New value of locationId.
     */
    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }


    /**
     * Gets locationId.
     *
     * @return Value of locationId.
     */
    public Long getLocationId() {
        return locationId;
    }

    /**
     * Gets trashId.
     *
     * @return Value of trashId.
     */
    public Long getTrashId() {
        return trashId;
    }

    /**
     * Sets new trashId.
     *
     * @param trashId New value of trashId.
     */
    public void setTrashId(Long trashId) {
        this.trashId = trashId;
    }
}
