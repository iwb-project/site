package org.iwb.site.bo;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public class PageDataHolder<T> {

    private Iterable<T> values;

    private Long totalItems;

    private Long totalPages;

    private Long currentPage;

    private String nextPageLink;

    private String previousPageLink;

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public String getNextPageLink() {
        return nextPageLink;
    }

    public void setNextPageLink(String nextPageLink) {
        this.nextPageLink = nextPageLink;
    }

    public String getPreviousPageLink() {
        return previousPageLink;
    }

    public void setPreviousPageLink(String previousPageLink) {
        this.previousPageLink = previousPageLink;
    }

    /**
     * Default constructor.
     */
    public PageDataHolder() {
        // void
    }

    public Iterable<T> getValues() {
        return values;
    }

    public void setValues(Iterable<T> values) {
        this.values = values;
    }
}
