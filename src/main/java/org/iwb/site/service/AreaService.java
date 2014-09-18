package org.iwb.site.service;

import org.iwb.site.bo.Area;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public interface AreaService {

    Area findById(String areaId);

    Iterable<Area> findAll();

    void save(Area area);
}
