package com.mizu20040814.accountingsoftware.transaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transaction-items")
@Getter
@Setter
@NoArgsConstructor
public class TransactionItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long transactionId;

    private Long productId;

    @Column(nullable = false,length = 100)
    private String productName;

    @Column(nullable = false)
    private int unitPrice;

    @Column(nullable = false)
    private int quantity;
}
