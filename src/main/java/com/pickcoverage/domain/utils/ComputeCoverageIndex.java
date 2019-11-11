package com.pickcoverage.domain.utils;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public enum ComputeCoverageIndex {

    /**
     * Basic compute coverage index.
     */
    basic(com.pickcoverage.domain.entities.CoverageEntity.class);

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
