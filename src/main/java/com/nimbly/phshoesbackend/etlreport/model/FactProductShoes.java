package com.nimbly.phshoesbackend.etlreport.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Entity
@Table(name = "FACT_PRODUCT_SHOES", schema = "PRODUCTION_MARTS", catalog = "PH_SHOES_DB")
@Immutable
@Getter
@NoArgsConstructor
public class FactProductShoes {

    @Id
    @Column(name = "DWID")
    private String dwid;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "DAY")
    private Integer day;
}
