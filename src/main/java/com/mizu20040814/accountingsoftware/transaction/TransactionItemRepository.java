package com.mizu20040814.accountingsoftware.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionItemRepository extends JpaRepository<TransactionItem,Long> {

    // 指定日の取引アイテムを取得（商品別集計で使用）
    @Query("SELECT ti FROM TransactionItem ti WHERE ti.transaction.createdAt BETWEEN :start AND :end")
    List<TransactionItem> findByTransactionCreatedAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );


}
