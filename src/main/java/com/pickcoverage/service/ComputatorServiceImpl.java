package com.pickcoverage.service;

import com.pickcoverage.domain.coverages.Bike;
import com.pickcoverage.domain.coverages.Electronics;
import com.pickcoverage.domain.coverages.Jewelry;
import com.pickcoverage.domain.coverages.SportsEquipment;
import com.pickcoverage.domain.entities.CoverageEntity;
import com.pickcoverage.domain.repository.*;
import com.pickcoverage.domain.utils.ComputeCoverageIndex;
import com.pickcoverage.service.dto.CoverageResponseDto;
import com.pickcoverage.service.dto.ErrorDto;
import com.pickcoverage.service.dto.IDto;
import com.pickcoverage.utils.CoverageException;
import com.pickcoverage.web.requests.CoverageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Service
@Transactional
public class ComputatorServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ComputatorServiceImpl.class);

    /**
     * The Bike repository.
     */
    @Autowired
    IBikeRepository iBikeRepository;

    /**
     * The Electronics repository.
     */
    @Autowired
    IElectronicsRepository iElectronicsRepository;

    /**
     * The Jewelry repository.
     */
    @Autowired
    IJewelryRepository iJewelryRepository;

    /**
     * The Sports equipment repository.
     */
    @Autowired
    ISportsEquipmentRepository iSportsEquipmentRepository;

    /**
     * The Coverage repository.
     */
    @Autowired
    ICoverageRepository iCoverageRepository;

    private static Map<String, ComputatorService> COMPUTATOR_MAP;

    private static Map<String, Double> RISK_PREM_PERC_PER_COVERAGE_MAP = new TreeMap<String, Double>();

    /**
     * Instantiates a new Computator service.
     */
    public ComputatorServiceImpl() {

        COMPUTATOR_MAP = new TreeMap<String, ComputatorService>();
        COMPUTATOR_MAP.put("basic", new BasicPremiumRiskCalcImpl());

    }

    /**
     * Calculate double.
     *
     * @param valToCalcOne              the val to calc one
     * @param valToCalcTwo              the val to calc two
     * @param typeOfCoverageComputation the type of coverage computation
     * @return the double
     * @throws CoverageException the coverage exception
     */
    public static Double calculate(Double valToCalcOne, Double valToCalcTwo, String typeOfCoverageComputation) throws CoverageException {

        Double computationResult = null;

        if (COMPUTATOR_MAP.containsKey(typeOfCoverageComputation)) {
            computationResult = COMPUTATOR_MAP.get(typeOfCoverageComputation).computeBasicRiskPremiumValue(valToCalcOne, valToCalcTwo);
        } else {
            throw new CoverageException("Unsupported Operation: " + typeOfCoverageComputation);
        }

        return computationResult;
    }

    /**
     * Save coverage response dto.
     *
     * @param coverageRequest      the coverage request
     * @param computeCoverageIndex the compute coverage index
     * @return the coverage response dto
     * @throws CoverageException the coverage exception
     */
    public IDto save(CoverageRequest coverageRequest, ComputeCoverageIndex computeCoverageIndex) throws CoverageException {

        initCoveragePercMap();

        if (verifyIfMinMaxLimitForCoverAmPerTypeIsRequestedWrong(coverageRequest.getTypeOfProduct().name(), coverageRequest.getCoverageAmount())) {
            return new ErrorDto("Incorrect Coverage input values for product type: " + coverageRequest.getTypeOfProduct().name());
        }

        Double premiumToPay = calculate(coverageRequest.getCoverageAmount(), RISK_PREM_PERC_PER_COVERAGE_MAP.get(coverageRequest.getTypeOfProduct().name()), computeCoverageIndex.toString());
        Double riskPremPerc = RISK_PREM_PERC_PER_COVERAGE_MAP.get(coverageRequest.getTypeOfProduct().name());

       CoverageEntity coverageEntit = iCoverageRepository.save(new CoverageEntity(coverageRequest.getTypeOfProduct(), coverageRequest.getCoverageAmount(), premiumToPay, riskPremPerc));
       return coverageEntit != null ?
               new CoverageResponseDto(coverageEntit.getTypeOfCoverageIndex().name(), coverageEntit.getRiskPremiumToBePaid()) :
               new ErrorDto("Something has happened, Coverage not saved, try again...");
    }

    private void initCoveragePercMap() {

        Bike bike = iBikeRepository.findOne(1l);
        RISK_PREM_PERC_PER_COVERAGE_MAP.put("bike", bike.getRiskPercentageAsNum());

        Jewelry jewelry = iJewelryRepository.findOne(1l);
        RISK_PREM_PERC_PER_COVERAGE_MAP.put("jewelry", jewelry.getRiskPercentageAsNum());

        Electronics electronics = iElectronicsRepository.findOne(1l);
        RISK_PREM_PERC_PER_COVERAGE_MAP.put("electronics", electronics.getRiskPercentageAsNum());

        SportsEquipment sportsEquipment = iSportsEquipmentRepository.findOne(1l);
        RISK_PREM_PERC_PER_COVERAGE_MAP.put("sportsEquipment", sportsEquipment.getRiskPercentageAsNum());
    }

    private boolean verifyIfMinMaxLimitForCoverAmPerTypeIsRequestedWrong(String typeOfCover, Double requestedCoverageAmount) {

        boolean brokenTheLimits = false;

        if (typeOfCover.equals("bike")) {

            Bike bike = iBikeRepository.findOne(1l);
            brokenTheLimits = bike.getMinimumAmount() > requestedCoverageAmount ? true : (bike.getMaximumAmount() < requestedCoverageAmount);

        } else if (typeOfCover.equals("jewelry")) {

            Jewelry jewelry = iJewelryRepository.findOne(1l);
            brokenTheLimits = jewelry.getMinimumAmount() < requestedCoverageAmount ? true : (jewelry.getMaximumAmount() < requestedCoverageAmount);

        } else if (typeOfCover.equals("electronics")) {

            Electronics electronics = iElectronicsRepository.findOne(1l);
            brokenTheLimits = electronics.getMinimumAmount() < requestedCoverageAmount ? true : (electronics.getMaximumAmount() < requestedCoverageAmount);

        } else if (typeOfCover.equals("sportsEquipment")) {

            SportsEquipment sportsEquipment = iSportsEquipmentRepository.findOne(1l);
            brokenTheLimits = sportsEquipment.getMinimumAmount() > requestedCoverageAmount ? true : (sportsEquipment.getMaximumAmount() < requestedCoverageAmount);
        }

        return brokenTheLimits;
    }

}

