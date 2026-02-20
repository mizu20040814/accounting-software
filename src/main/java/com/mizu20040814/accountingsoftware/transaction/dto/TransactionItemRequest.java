package com.mizu20040814.accountingsoftware.transaction.dto;

import lombok.Data;

@Data
public class TransactionItemRequest {
    private final Long productId;
    private final int quantity;
}
