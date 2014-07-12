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

    private String icon;

    private String color;

    private String snippet;

    /**
     * Default constructor.
     */
    public Trash() {
        // void
    }

    public Trash(final String snippet) {
        this.snippet = snippet;
    }

    public Trash(final String icon, final String color) {
        this.icon = icon;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets color.
     *
     * @return Value of color.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets new icon.
     *
     * @param icon New value of icon.
     */
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    /**
     * Sets new color.
     *
     * @param color New value of color.
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Gets icon.
     *
     * @return Value of icon.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Sets new snippet.
     *
     * @param snippet New value of snippet.
     */
    public void setSnippet(final String snippet) {
        this.snippet = snippet;
    }

    /**
     * Gets snippet.
     *
     * @return Value of snippet.
     */
    public String getSnippet() {
        return snippet;
    }
}
