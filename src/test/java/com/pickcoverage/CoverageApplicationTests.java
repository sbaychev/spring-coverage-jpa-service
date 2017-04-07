package com.pickcoverage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickcoverage.domain.coverages.Bike;
import com.pickcoverage.domain.coverages.Electronics;
import com.pickcoverage.domain.coverages.Jewelry;
import com.pickcoverage.domain.coverages.SportsEquipment;
import com.pickcoverage.domain.repository.IBikeRepository;
import com.pickcoverage.domain.repository.IElectronicsRepository;
import com.pickcoverage.domain.repository.IJewelryRepository;
import com.pickcoverage.domain.repository.ISportsEquipmentRepository;
import com.pickcoverage.domain.utils.ComputeCoverageIndex;
import com.pickcoverage.domain.utils.TypeOfCoverageIndex;
import com.pickcoverage.service.dto.CoverageResponseDto;
import com.pickcoverage.web.requests.CoverageRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.springframework.boot.autoconfigure.web.HttpEncodingProperties.DEFAULT_CHARSET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * The type Coverage application tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CoverageApplicationTests {

    private MockRestServiceServer server;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplate rest;

    @Autowired
    private IBikeRepository iBikeRepository;

    @Autowired
    private IElectronicsRepository iElectronicsRepository;

    @Autowired
    private IJewelryRepository iJewelryRepository;

    @Autowired
    private ISportsEquipmentRepository iSportsEquipmentRepository;

    /**
     * Sets .
     */
    @Before
    public void setup() {
        this.server = MockRestServiceServer.createServer(rest);
        iBikeRepository.save(new Bike(Double.valueOf(0d), Double.valueOf(3000d), Double.valueOf(30d)));
        iElectronicsRepository.save(new Electronics(Double.valueOf(500d), Double.valueOf(6000d), Double.valueOf(35d)));
        iJewelryRepository.save(new Jewelry(Double.valueOf(500d), Double.valueOf(10000d), Double.valueOf(5d)));
        iSportsEquipmentRepository.save(new SportsEquipment(Double.valueOf(0d), Double.valueOf(20000d), Double.valueOf(30d)));
    }

    /**
     * Teardown.
     */
    @After
    public void teardown() {
        this.server = null;
    }

    /**
     * To calc create coverage test.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    public void toCalcCreateCoverageTest() throws JsonProcessingException {

        CoverageRequest coverageRequest = new CoverageRequest(Double.valueOf(500d), TypeOfCoverageIndex.bike, ComputeCoverageIndex.basic);

        ObjectMapper mapper = new ObjectMapper();

        String jsonCoverageRequest = mapper.writeValueAsString(coverageRequest);

        // some peculated value as not to hamper the rest call for now
        CoverageResponseDto coverageResponseDto = new CoverageResponseDto(TypeOfCoverageIndex.bike.name(), BigDecimal.valueOf(150d).toString());

        String jsonCoverageResponse = mapper.writeValueAsString(coverageResponseDto);

        String postUrl = "http://localhost:8080/createCoverage";

        this.server.expect(requestTo(postUrl))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(new MediaType("application", "json", DEFAULT_CHARSET)))
                .andExpect(content().string(jsonCoverageRequest))
                .andRespond(withSuccess(jsonCoverageResponse, new MediaType("application", "json", DEFAULT_CHARSET)));

    }

    /**
     * To calc create coverage failure test.
     *
     * @throws JsonProcessingException the json processing exception
     */
    @Test
    public void toCalcCreateCoverageFailureTest() throws JsonProcessingException {

        String postUrl = "http://localhost:8080/createCoverage";

        CoverageRequest coverageRequest = new CoverageRequest(5000000d, TypeOfCoverageIndex.bike, ComputeCoverageIndex.basic);
        ObjectMapper mapper = new ObjectMapper();
        String jsonCoverageRequest = mapper.writeValueAsString(coverageRequest);

        this.server.expect(requestTo(postUrl))
                        .andExpect(method(HttpMethod.POST))
                        .andExpect(content().contentType(new MediaType("application", "json", DEFAULT_CHARSET)))
                        .andExpect(content().string(jsonCoverageRequest))
                        .andRespond(withServerError());

    }

}
