package com.mizu20040814.accountingsoftware.transaction.dto;

import com.mizu20040814.accountingsoftware.transaction.TransactionItem;
import lombok.Data;

@Data
public class TransactionItemResponse {
    private String productName;
    private int unitPrice;
    private int quantity;

    public static TransactionItemResponse from(TransactionItem transactionItem){
        TransactionItemResponse newItemResponse = new TransactionItemResponse();
        newItemResponse.productName = transactionItem.getProductName();
        newItemResponse.unitPrice = transactionItem.getUnitPrice();
        newItemResponse.quantity = transactionItem.getQuantity();
        return newItemResponse;
    }

}
