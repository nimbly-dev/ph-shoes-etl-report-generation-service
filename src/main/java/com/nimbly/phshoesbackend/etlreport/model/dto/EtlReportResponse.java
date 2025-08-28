package com.nimbly.phshoesbackend.etlreport.model.dto;

import com.nimbly.phshoesbackend.etlreport.model.Section;
import lombok.Data;

public record EtlReportResponse(String asOfDate, String timezone, Section raw, Section fact) {}