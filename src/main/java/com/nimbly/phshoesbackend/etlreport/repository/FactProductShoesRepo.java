package com.nimbly.phshoesbackend.etlreport.repository;

import com.nimbly.phshoesbackend.etlreport.model.FactProductShoes;
import com.nimbly.phshoesbackend.etlreport.repository.projection.BrandCountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FactProductShoesRepo extends JpaRepository<FactProductShoes, String> {
    @Query("select count(f) from FactProductShoes f")
    long total();

    @Query("""
      select f.brand as brand, count(f) as count
      from FactProductShoes f
      group by f.brand
      order by count(f) desc
    """)
    List<BrandCountView> countByBrand();

    @Query("""
      select count(f)
      from FactProductShoes f
      where function('DATE_FROM_PARTS', f.year, f.month, f.day) = :d
    """)
    long countOnDate(@Param("d") LocalDate d);

    @Query("""
      select f.brand as brand, count(f) as count
      from FactProductShoes f
      where function('DATE_FROM_PARTS', f.year, f.month, f.day) = :d
      group by f.brand
      order by count(f) desc
    """)
    List<BrandCountView> countByBrandOnDate(@Param("d") LocalDate d);
}