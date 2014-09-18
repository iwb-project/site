package org.iwb.site.repository;

import org.iwb.site.bo.Area;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public interface AreaRepository {

    Area findAreaById(String areaId);

    Area save(Area area);

    Iterable<Area> findAll();
}
