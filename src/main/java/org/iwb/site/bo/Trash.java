package org.iwb.site.bo;

import org.jongo.marshall.jackson.oid.Id;

import java.util.List;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class Trash {
    
    @Id
    private Long id;
    
    private List<String> materials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    /**
     * Default constructor.
     */
    public Trash() {
        // void

    }

}
