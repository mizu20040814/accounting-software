package com.mizu20040814.accountingsoftware.report.dto;

import lombok.Data;

@Data
public class HourlySalesResponse {

    private final int hour;
    private final int transactionCount;
    private final int totalAmount;

}
