package org.iwb.site.service;

import org.iwb.site.bo.Area;
import org.iwb.site.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaRepository repository;

    @Override
    public Area findById(final String areaId) {
        return this.repository.findAreaById(areaId);
    }

    @Override
    public Iterable<Area> findAll() {
        return this.repository.findAll();
    }

    @Override
    public void save(final Area area) {
        this.repository.save(area);
    }
}
