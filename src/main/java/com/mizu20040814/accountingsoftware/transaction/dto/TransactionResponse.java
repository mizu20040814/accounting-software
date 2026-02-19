package com.mizu20040814.accountingsoftware.transaction.dto;

import com.mizu20040814.accountingsoftware.transaction.Transaction;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private final Long id;
    private final int totalAmount;
    private final int receivedAmount;
    private final int changeAmount;
    private final LocalDateTime createdAt;

    public static TransactionResponse from(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getTotalAmount(),
                transaction.getReceivedAmount(),
                transaction.getChangeAmount(),
                transaction.getCreatedAt()
        );
    }
}
