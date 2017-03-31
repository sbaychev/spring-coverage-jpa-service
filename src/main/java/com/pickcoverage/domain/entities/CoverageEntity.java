package com.pickcoverage.domain.entities;

import com.pickcoverage.domain.utils.TypeOfCoverageIndex;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Entity
@Table(name="coverage")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CoverageEntity extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column
    private TypeOfCoverageIndex typeOfCoverageIndex;

    @Column
    private Double amountToBeCovered;

    @Column
    private Double riskPremiumToBePaid;

    @Column
    private Double riskPercentageAsNum;

}
