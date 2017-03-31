package com.pickcoverage.domain.utils;

import com.pickcoverage.domain.entities.CoverageEntity;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public enum ComputeCoverageIndex {

    /**
     * Basic compute coverage index.
     */
    basic(CoverageEntity.class);

    /**
     * The Entity class.
     */
    final Class entityClass;

    ComputeCoverageIndex(Class entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Gets entity class.
     *
     * @return the entity class
     */
    public Class getEntityClass() {
        return entityClass;
    }
}
