package com.pickcoverage.web.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pickcoverage.domain.utils.ComputeCoverageIndex;
import com.pickcoverage.domain.utils.TypeOfCoverageIndex;
import lombok.*;

import java.io.Serializable;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoverageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double coverageAmount;

    private TypeOfCoverageIndex typeOfCoverageIndex;

    private ComputeCoverageIndex computeCoverageIndex;

    @Override
    public String toString() {
        return "CoverageRequest{" + "coverageAmount=" + coverageAmount
            + ", typeOfProduct=" + typeOfCoverageIndex.name()
            + ", computeCoverageIndex=" + computeCoverageIndex.name()
            + '}';
    }
}
