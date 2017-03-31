package com.pickcoverage.utils;

import lombok.*;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class SearchError {

    /**
     * The Code.
     */
    String code;

    /**
     * The Message.
     */
    String message;

}
