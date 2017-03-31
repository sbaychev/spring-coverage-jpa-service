package com.pickcoverage.service.dto;

import lombok.*;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CoverageResponseDto implements IDto {

    private static final long serialVersionUID = 1L;

    private String coverageType;

    private Double premiumAmountToBePaid;

}
