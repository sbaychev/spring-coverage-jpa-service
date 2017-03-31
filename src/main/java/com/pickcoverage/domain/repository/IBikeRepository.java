package com.pickcoverage.domain.repository;

import com.pickcoverage.domain.coverages.Bike;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface IBikeRepository extends IActiveRepository<Bike>, JpaSpecificationExecutor<Bike> {
}
