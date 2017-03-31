package com.pickcoverage.domain.entities;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by stefanbaychev on 3/30/17.
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = false, of = { "id" })
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private Long id;

}
