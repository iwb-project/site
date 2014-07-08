package org.iwb.site.bo;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class Component {

    private String name;

    private String description;

    private String materialId;

    private Material material;


    /**
     * Default constructor.
     */
    public Component() {
        // void
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }
}
