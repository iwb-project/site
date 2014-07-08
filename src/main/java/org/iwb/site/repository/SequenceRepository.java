package org.iwb.site.repository;

/**
 * TODO: document me.
 *
 * @author mathieu.pousse@zenika.com
 */
public interface SequenceRepository {

    Long getNextValue(String sequence);

}
