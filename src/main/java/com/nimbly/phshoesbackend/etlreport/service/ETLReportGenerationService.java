package com.nimbly.phshoesbackend.etlreport.service;

import com.nimbly.phshoesbackend.etlreport.model.dto.EtlReportResponse;

import java.time.LocalDate;

public interface ETLReportGenerationService {
    EtlReportResponse buildReport(LocalDate date, String timezone);
}
