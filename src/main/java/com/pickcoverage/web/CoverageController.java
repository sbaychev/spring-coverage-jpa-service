package com.pickcoverage.web;

import com.pickcoverage.domain.utils.ComputeCoverageIndex;
import com.pickcoverage.domain.utils.ComputeCoverageIndexBinder;
import com.pickcoverage.domain.utils.TypeOfCoverageIndex;
import com.pickcoverage.domain.utils.TypeOfCoverageIndexBinder;
import com.pickcoverage.service.ComputatorServiceImpl;
import com.pickcoverage.utils.SearchError;
import com.pickcoverage.web.requests.CoverageRequest;
import java.util.concurrent.Callable;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@RestController
public class CoverageController {

    private static final Logger LOG = LoggerFactory.getLogger(CoverageController.class);

    @Autowired
    private ComputatorServiceImpl computatorService;

    /**
     * Init binder.
     *
     * @param webDataBinder the web data binder
     */
    @InitBinder
    void initBinder(WebDataBinder webDataBinder) {

        webDataBinder.registerCustomEditor(TypeOfCoverageIndex.class, new TypeOfCoverageIndexBinder());
        webDataBinder.registerCustomEditor(ComputeCoverageIndex.class, new ComputeCoverageIndexBinder());
    }

    /**
     * Rest rest template.
     *
     * @param builder the builder
     * @return the rest template
     */
    @Bean
    public RestTemplate rest(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * Calculate callable.
     *
     * @param coverageRequest the coverage request
     * @return the callable
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity> createCoverage(@Valid @RequestBody CoverageRequest coverageRequest) {

        LOG.info(
            "Received a create coverage call with params: " + coverageRequest.getComputeCoverageIndex().toString() + " "
                + coverageRequest.getComputeCoverageIndex().name() + " " + coverageRequest.getCoverageAmount());

        return () -> ResponseEntity
            .ok(computatorService.save(coverageRequest, coverageRequest.getComputeCoverageIndex()));
    }

    /*
     * Handle invalid payload search error.
     *
     *   @param e the e
     *   @return the search error
     */
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class,
        MethodArgumentNotValidException.class})
    private @ResponseBody
    SearchError handleInvalidPayload(Exception e) {

        LOG.error("Data Integrity Violation: ", e);

        SearchError error = new SearchError();

        error.setCode("0001");
        error.setMessage("Data Integrity Violation! Check Log for exception information");

        return error;
    }
}
