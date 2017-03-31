package com.pickcoverage.domain.repository;

import com.pickcoverage.domain.entities.CoverageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface ICoverageRepository extends JpaRepository<CoverageEntity, Long>, JpaSpecificationExecutor<CoverageEntity> {
}
