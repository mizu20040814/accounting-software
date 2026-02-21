package com.mizu20040814.accountingsoftware.report.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductSalesResponse {

    private final String productName;
    private final int quantitySold;
    private final int totalAmount;

}
