package com.pickcoverage.service;

/**
 * Created by stefanbaychev on 3/30/17.
 */
public interface ComputatorService {

    /**
     * Compute basic risk premium value double.
     *
     * @param coverValue         the cover value
     * @param riskValueInNumPerc the risk value in num perc
     * @return the double
     */
    Double computeBasicRiskPremiumValue(Double coverValue, Double riskValueInNumPerc);

}
