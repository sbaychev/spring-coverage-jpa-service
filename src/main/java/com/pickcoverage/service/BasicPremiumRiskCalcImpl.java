package com.pickcoverage.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Component
@NoArgsConstructor
public class BasicPremiumRiskCalcImpl implements ComputatorService {


    @Override
    public BigDecimal computeBasicRiskPremiumValue(Double coverValue, Double riskValueInNumPerc) {
        return (BigDecimal.valueOf(coverValue).multiply(BigDecimal.valueOf(riskValueInNumPerc))).divide(BigDecimal.valueOf(100));
    }
}
