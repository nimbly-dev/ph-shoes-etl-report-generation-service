package com.nimbly.phshoesbackend.etlreport.service.impl;

import com.nimbly.phshoesbackend.etlreport.model.BrandCount;
import com.nimbly.phshoesbackend.etlreport.model.Section;
import com.nimbly.phshoesbackend.etlreport.model.dto.EtlReportResponse;
import com.nimbly.phshoesbackend.etlreport.repository.FactProductShoesRepo;
import com.nimbly.phshoesbackend.etlreport.repository.RawProductShoesRepo;
import com.nimbly.phshoesbackend.etlreport.service.ETLReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class ETLReportGenerationServiceImpl implements ETLReportGenerationService {

    @Autowired
    private RawProductShoesRepo rawRepo;
    @Autowired
    private FactProductShoesRepo factRepo;

    @Override
    public EtlReportResponse buildReport(LocalDate date, String timezone) {
        LocalDate d = (date != null) ? date : LocalDate.now();

        // RAW
        long rawTotal   = rawRepo.total();
        long rawOnDate  = rawRepo.countOnDate(d, timezone);
        var rawByBrand  = rawRepo.countByBrand().stream()
                .map(p -> new BrandCount(p.getBrand(), p.getCount()))
                .toList();
        var rawByBrandOnDate = rawRepo.countByBrandOnDate(d, timezone).stream()
                .map(p -> new BrandCount(p.getBrand(), p.getCount()))
                .toList();

        // FACT
        long factTotal  = factRepo.total();
        long factOnDate = factRepo.countOnDate(d);
        var factByBrand = factRepo.countByBrand().stream()
                .map(p -> new BrandCount(p.getBrand(), p.getCount()))
                .toList();
        var factByBrandOnDate = factRepo.countByBrandOnDate(d).stream()
                .map(p -> new BrandCount(p.getBrand(), p.getCount()))
                .toList();

        return new EtlReportResponse(
                d.toString(),
                timezone,
                new Section(rawTotal,  rawOnDate,  rawByBrand,  rawByBrandOnDate),
                new Section(factTotal, factOnDate, factByBrand, factByBrandOnDate)
        );
    }
}
