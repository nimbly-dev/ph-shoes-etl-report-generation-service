package com.nimbly.phshoesbackend.etlreport.model;


import java.util.List;


public record Section(long total, long onDate, List<BrandCount> byBrand, List<BrandCount> byBrandOnDate) {}
