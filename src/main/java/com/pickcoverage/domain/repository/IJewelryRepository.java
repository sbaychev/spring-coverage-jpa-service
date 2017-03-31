package com.pickcoverage.domain.repository;

import com.pickcoverage.domain.coverages.Jewelry;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface IJewelryRepository extends IActiveRepository<Jewelry>, JpaSpecificationExecutor<Jewelry> {
}
