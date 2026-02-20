package com.mizu20040814.accountingsoftware.transaction;

import com.mizu20040814.accountingsoftware.product.Product;
import com.mizu20040814.accountingsoftware.product.ProductRepository;
import com.mizu20040814.accountingsoftware.transaction.dto.TransactionItemRequest;
import com.mizu20040814.accountingsoftware.transaction.dto.TransactionRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;

    public TransactionService(TransactionRepository transactionRepository, ProductRepository productRepository) {
        this.transactionRepository = transactionRepository;
        this.productRepository = productRepository;
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定されたIDの取引が見つかりません: " + id));
    }

    @Transactional
    public Transaction create(TransactionRequest request){

        int totalAmount = 0;
        final List<TransactionItemRequest> itemsRequest = request.getItems();
        List<TransactionItem> newItems = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();

        // ==================== 検証フェーズ ====================
        for(TransactionItemRequest item : itemsRequest){
            final Long productId = item.getProductId();
            final int itemQuantity = item.getQuantity();

            final Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException(
                            "商品が見つかりません: " + productId));

            if(product.getStock() < itemQuantity){
                throw new IllegalArgumentException(
                        "在庫不足です: " + product.getName()
                                + "（在庫: " + product.getStock()
                                + ", 要求: " + itemQuantity + "）");
            }

            totalAmount += product.getPrice() * itemQuantity;

            TransactionItem newItem = new TransactionItem();
            newItem.setProduct(product);
            newItem.setProductName(product.getName());
            newItem.setUnitPrice(product.getPrice());
            newItem.setQuantity(itemQuantity);
            newItems.add(newItem);

            productsToUpdate.add(product);
        }

        // 預かり金不足チェック
        if (request.getReceivedAmount() < totalAmount) {
            throw new IllegalArgumentException(
                    "預かり金が不足しています（合計: " + totalAmount
                            + "円, 預かり: " + request.getReceivedAmount() + "円）");
        }

        // ==================== 更新フェーズ ====================
        // 在庫を減算
        for (int i = 0; i < productsToUpdate.size(); i++) {
            Product product = productsToUpdate.get(i);
            int quantity = itemsRequest.get(i).getQuantity();
            product.setStock(product.getStock() - quantity);
            productRepository.save(product);
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setTotalAmount(totalAmount);
        newTransaction.setReceivedAmount(request.getReceivedAmount());
        newTransaction.setChangeAmount(request.getReceivedAmount() - totalAmount);
        newTransaction.setCreatedAt(LocalDateTime.now());

        // 各アイテムにトランザクション参照を設定
        for (TransactionItem item : newItems) {
            item.setTransaction(newTransaction);
        }
        newTransaction.setItems(newItems);

        return transactionRepository.save(newTransaction);
    }
}