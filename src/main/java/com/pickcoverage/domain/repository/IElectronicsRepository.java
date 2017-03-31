package com.pickcoverage.domain.repository;

import com.pickcoverage.domain.coverages.Electronics;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface IElectronicsRepository extends IActiveRepository<Electronics>, JpaSpecificationExecutor<Electronics> {
}
