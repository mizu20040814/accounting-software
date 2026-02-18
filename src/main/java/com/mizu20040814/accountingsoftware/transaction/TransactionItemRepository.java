package com.mizu20040814.accountingsoftware.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem,Long> {
}
