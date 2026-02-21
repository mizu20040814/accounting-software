package com.mizu20040814.accountingsoftware.report;

import com.mizu20040814.accountingsoftware.report.dto.HourlySalesResponse;
import com.mizu20040814.accountingsoftware.report.dto.ProductSalesResponse;
import com.mizu20040814.accountingsoftware.report.dto.SummaryResponse;
import com.mizu20040814.accountingsoftware.transaction.Transaction;
import com.mizu20040814.accountingsoftware.transaction.TransactionItem;
import com.mizu20040814.accountingsoftware.transaction.TransactionItemRepository;
import com.mizu20040814.accountingsoftware.transaction.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final TransactionRepository transactionRepository;
    private final TransactionItemRepository transactionItemRepository;

    public ReportService(TransactionRepository transactionRepository,TransactionItemRepository transactionItemRepository){
        this.transactionRepository = transactionRepository;
        this.transactionItemRepository = transactionItemRepository;
    }

    public SummaryResponse getSummary(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(start, end);

        int totalAmount = transactions.stream()
                .mapToInt(Transaction::getTotalAmount)
                .sum();

        return new SummaryResponse(date, totalAmount, transactions.size());
    }

    public List<ProductSalesResponse> getByProduct(LocalDate date){
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<TransactionItem> productSales = transactionItemRepository.findByTransactionCreatedAtBetween(start,end);
        List<ProductSalesResponse> responses = productSales.stream()
                .collect(Collectors.groupingBy(TransactionItem::getProductName))
                .entrySet()
                .stream()
                .map(entry ->{
                    String productName = entry.getKey();
                    List<TransactionItem> items = entry.getValue();

                    int quantitySold = items.stream()
                            .mapToInt(TransactionItem::getQuantity)
                            .sum();

                    int totalAmount = items.stream()
                            .mapToInt(item -> item.getUnitPrice() * item.getQuantity())
                            .sum();

                    return new ProductSalesResponse(productName,quantitySold,totalAmount);
                })
                .toList();

        return responses;
    }

    public List<HourlySalesResponse> getByHour(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Transaction> transactions = transactionRepository.findByCreatedAtBetween(start, end);

        return transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getCreatedAt().getHour()))
                .entrySet()
                .stream()
                .map(entry -> {
                    int hour = entry.getKey();
                    List<Transaction> hourlyTransactions = entry.getValue();

                    int totalAmount = hourlyTransactions.stream()
                            .mapToInt(Transaction::getTotalAmount)
                            .sum();

                    return new HourlySalesResponse(hour, hourlyTransactions.size(), totalAmount);
                })
                .sorted(Comparator.comparing(HourlySalesResponse::getHour))
                .toList();

    }

}
