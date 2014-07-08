package org.iwb.site.bo;

import java.util.List;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class Item extends ItemEssentials {

    private String description;

    private String barcode;

    private String wasteTypeId;

    private WasteType wasteType;

    private Long views;

    private String authorId;

    private String lastEditAuthorId;

    private List<Component> components;

    private long version;

    /**
     * Default constructor.
     */
    public Item() {
        // void
    }

    public Long getViews() {
        return this.views;
    }

    public void setViews(final Long views) {
        this.views = views;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(final String authorId) {
        this.authorId = authorId;
    }

    public String getLastEditAuthorId() {
        return this.lastEditAuthorId;
    }

    public void setLastEditAuthorId(final String lastEditAuthorId) {
        this.lastEditAuthorId = lastEditAuthorId;
    }

    /**
     * Gets wasteType.
     *
     * @return Value of wasteType.
     */
    public WasteType getWasteType() {
        return this.wasteType;
    }

    /**
     * Gets barcode.
     *
     * @return Value of barcode.
     */
    public String getBarcode() {
        return this.barcode;
    }

    /**
     * Sets new wasteTypeId.
     *
     * @param wasteTypeId New value of wasteTypeId.
     */
    public void setWasteTypeId(final String  wasteTypeId) {
        this.wasteTypeId = wasteTypeId;
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets new wasteType.
     *
     * @param wasteType New value of wasteType.
     */
    public void setWasteType(final WasteType wasteType) {
        this.wasteType = wasteType;
    }

    /**
     * Gets wasteTypeId.
     *
     * @return Value of wasteTypeId.
     */
    public String getWasteTypeId() {
        return this.wasteTypeId;
    }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Sets new barcode.
     *
     * @param barcode New value of barcode.
     */
    public void setBarcode(final String barcode) {
        this.barcode = barcode;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(final long version) {
        this.version = version;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public void setComponents(final List<Component> components) {
        this.components = components;
    }
}
