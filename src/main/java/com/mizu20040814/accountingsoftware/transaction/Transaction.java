package com.mizu20040814.accountingsoftware.transaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int totalAmount;

    @Column(nullable = false)
    private int receivedAmount;

    @Column(nullable = false)
    private int changeAmount;

    private LocalDateTime createdAt;
}
