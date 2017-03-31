package com.pickcoverage.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@MappedSuperclass
public class DeactivatableEntity extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @Column(name = "isactive")
    private Integer isActive = 1;

    /**
     * Is active boolean.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return getIsActive() > 0;
    }
}

