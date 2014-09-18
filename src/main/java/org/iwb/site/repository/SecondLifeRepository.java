package org.iwb.site.repository;

import org.iwb.site.bo.SecondLife;

/**
 * TODO document me
 *
 * @author Mathieu POUSSE <mathieu.pousse@zenika.com>
 */
public interface SecondLifeRepository {

    Long findSecondLifeByLocationAndMaterial(Long locationId, String materialId);

    SecondLife save(SecondLife secondLife);

}
