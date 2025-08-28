package com.nimbly.phshoesbackend.etlreport.repository;

import com.nimbly.phshoesbackend.etlreport.model.RawProductShoes;
import com.nimbly.phshoesbackend.etlreport.repository.projection.BrandCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RawProductShoesRepo extends JpaRepository<RawProductShoes, String> {
    @Query("select count(r) from RawProductShoes r")
    long total();

    @Query("""
      select r.brand as brand, count(r) as count
      from RawProductShoes r
      group by r.brand
      order by count(r) desc
    """)
    List<BrandCountView> countByBrand();

    @Query(value = """
      select count(*)
      from PH_SHOES_DB.RAW.RAW_PRODUCT_SHOES_RAW
      where cast(CONVERT_TIMEZONE(:tz, LOADED_AT) as date) = :d
      """, nativeQuery = true)
    long countOnDate(@Param("d") LocalDate d, @Param("tz") String tz);

    @Query(value = """
      select BRAND as brand, count(*) as count
      from PH_SHOES_DB.RAW.RAW_PRODUCT_SHOES_RAW
      where cast(CONVERT_TIMEZONE(:tz, LOADED_AT) as date) = :d
      group by BRAND
      order by count(*) desc
      """, nativeQuery = true)
    List<BrandCountView> countByBrandOnDate(@Param("d") LocalDate d, @Param("tz") String tz);
}