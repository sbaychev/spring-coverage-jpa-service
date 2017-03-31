package com.pickcoverage.domain.repository;

/**
 * Created by stefanbaychev on 3/30/17.
 */


import com.pickcoverage.domain.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * Generic repository for entities with active property.
 *
 * @param <T> type of entity
 */
@NoRepositoryBean
public interface IActiveRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {

    /**
     * Find all active entities.
     *
     * @return found entities
     */
    @Query("from #{#entityName} t where t.isActive = 1")
    List<T> findAll();

    /**
     * Find one active entity with given id.
     *
     * @param id id of entity
     * @return found entity
     */
    @Query("from #{#entityName} t where t.id = ?1 and t.isActive = 1")
    T getOne(Long id);

    /**
     * Find one entity by given id and active state.
     *
     * @param id     id of entity
     * @param active active state of entity
     * @return found entity
     */
    @Query("from #{#entityName} t where t.id = ?1 and t.isActive = ?2")
    Optional<T> findOneByIdAndIsActive(Long id, Integer active);
}