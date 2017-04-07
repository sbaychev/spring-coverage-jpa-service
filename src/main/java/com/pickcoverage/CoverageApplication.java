package com.pickcoverage;

import com.pickcoverage.domain.coverages.Bike;
import com.pickcoverage.domain.coverages.Electronics;
import com.pickcoverage.domain.coverages.Jewelry;
import com.pickcoverage.domain.coverages.SportsEquipment;
import com.pickcoverage.domain.repository.IBikeRepository;
import com.pickcoverage.domain.repository.IElectronicsRepository;
import com.pickcoverage.domain.repository.IJewelryRepository;
import com.pickcoverage.domain.repository.ISportsEquipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.pickcoverage.domain.repository")
@EntityScan(basePackages = "com.pickcoverage.domain")
public class CoverageApplication {

    private static final Logger LOG = LoggerFactory.getLogger(CoverageApplication.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CoverageApplication.class, args);
    }

    /**
     * Init command line runner for sample data
     *
     * @param iBikeRepository            the bike repository
     * @param iJewelryRepository         the jewelry repository
     * @param iElectronicsRepository     the electronics repository
     * @param iSportsEquipmentRepository the sports equipment repository
     * @return the command line runner
     */
    @Bean
    CommandLineRunner init(IBikeRepository iBikeRepository, IJewelryRepository iJewelryRepository, IElectronicsRepository iElectronicsRepository, ISportsEquipmentRepository iSportsEquipmentRepository) {

        return (args) -> {
            iBikeRepository.save(new Bike(Double.valueOf(0d), Double.valueOf(3000d), Double.valueOf(30d)));
            iElectronicsRepository.save(new Electronics(Double.valueOf(500d), Double.valueOf(6000d), Double.valueOf(35d)));
            iJewelryRepository.save(new Jewelry(Double.valueOf(500d), Double.valueOf(10000d), Double.valueOf(5d)));
            iSportsEquipmentRepository.save(new SportsEquipment(Double.valueOf(0d), Double.valueOf(20000d), Double.valueOf(30d)));

            LOG.info("Created some Default Coverage Templates to be used");
        };
    }
}
