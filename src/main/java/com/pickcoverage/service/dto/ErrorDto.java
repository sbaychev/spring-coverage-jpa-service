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
public class ErrorDto implements IDto {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private String errorMessage;

}
