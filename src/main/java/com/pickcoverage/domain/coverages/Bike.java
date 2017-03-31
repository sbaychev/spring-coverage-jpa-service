package com.pickcoverage.domain.coverages;

import com.pickcoverage.domain.entities.DeactivatableEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Bike extends DeactivatableEntity {

    private static final long serialVersionUID = 1L;

    @Column
    private Double minimumAmount;

    @Column
    private Double maximumAmount;

    @Column
    private Double riskPercentageAsNum;
}
