package com.mizu20040814.accountingsoftware.transaction.dto;

import lombok.Data;

@Data
public class TransactionItemRequest {
    private final Long id;
    private final int quantity;
}
