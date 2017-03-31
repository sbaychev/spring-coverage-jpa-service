package com.pickcoverage.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Component
@NoArgsConstructor
public class BasicPremiumRiskCalcImpl implements ComputatorService {


    @Override
    public Double computeBasicRiskPremiumValue(Double coverValue, Double riskValueInNumPerc) {
        return coverValue * (riskValueInNumPerc / 100);
    }
}
