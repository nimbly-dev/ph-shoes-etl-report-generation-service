package com.nimbly.phshoesbackend.etlreport.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

import java.time.OffsetDateTime;

@Entity
@Table(name = "RAW_PRODUCT_SHOES_RAW", schema = "RAW", catalog = "PH_SHOES_DB")
@Immutable
public class RawProductShoes {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "LOADED_AT")
    private OffsetDateTime loadedAt;
}
