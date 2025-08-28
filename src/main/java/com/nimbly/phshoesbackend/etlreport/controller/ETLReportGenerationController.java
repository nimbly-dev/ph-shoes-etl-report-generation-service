package com.nimbly.phshoesbackend.etlreport.controller;

import com.nimbly.phshoesbackend.etlreport.model.dto.EtlReportResponse;
import com.nimbly.phshoesbackend.etlreport.service.ETLReportGenerationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/api/v1/etl-report")
public class ETLReportGenerationController {

    @Autowired
    private ETLReportGenerationService etlReportGenerationService;

    @GetMapping
    public EtlReportResponse generateReport(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "Asia/Manila") String timezone
    ) {
        return etlReportGenerationService.buildReport(date, timezone);
    }
}
