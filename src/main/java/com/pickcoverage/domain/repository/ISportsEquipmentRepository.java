package com.pickcoverage.domain.repository;

import com.pickcoverage.domain.coverages.SportsEquipment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface ISportsEquipmentRepository extends IActiveRepository<SportsEquipment>, JpaSpecificationExecutor<SportsEquipment> {
}
