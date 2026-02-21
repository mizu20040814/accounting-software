package com.mizu20040814.accountingsoftware.report.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SummaryResponse {

    private final LocalDate date;
    private final int totalAmount;
    private final int transactionCount;

}
