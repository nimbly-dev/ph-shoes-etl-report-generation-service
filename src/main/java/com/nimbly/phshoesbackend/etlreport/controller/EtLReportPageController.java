package com.nimbly.phshoesbackend.etlreport.controller;

import com.nimbly.phshoesbackend.etlreport.model.dto.EtlReportResponse;
import com.nimbly.phshoesbackend.etlreport.service.ETLReportGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDate;


@Controller
@RequestMapping("/reports")
public class EtLReportPageController {

    @Autowired private ETLReportGenerationService service;

    /**
     * Server-rendered HTML page.
     * Example: GET /reports/etl?timezone=Asia/Manila
     */
    @GetMapping("/etl")
    public String etlReportPage(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(defaultValue = "Asia/Manila") String timezone,
            Model model
    ) {
        EtlReportResponse r = service.buildReport(date, timezone);
        model.addAttribute("r", r);
        return "etl-report";
    }
}