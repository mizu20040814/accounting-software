package com.mizu20040814.accountingsoftware.transaction.dto;

import com.mizu20040814.accountingsoftware.transaction.Transaction;
import com.mizu20040814.accountingsoftware.transaction.TransactionItem;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionResponse {
    private final Long id;
    private final int totalAmount;
    private final int receivedAmount;
    private final int changeAmount;
    private final LocalDateTime createdAt;
    private final List<TransactionItemResponse> items;

    public static TransactionResponse from(Transaction transaction){
        return new TransactionResponse(
                transaction.getId(),
                transaction.getTotalAmount(),
                transaction.getReceivedAmount(),
                transaction.getChangeAmount(),
                transaction.getCreatedAt(),
                transaction.getItems()
                        .stream()
                        .map(TransactionItemResponse::from)
                        .toList()
        );
    }
}
