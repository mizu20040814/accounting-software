package com.mizu20040814.accountingsoftware.transaction.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionRequest {
    private final int receivedAmount;
    private final List<TransactionItemRequest> items;
}
