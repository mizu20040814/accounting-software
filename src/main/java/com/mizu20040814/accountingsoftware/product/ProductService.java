package com.mizu20040814.accountingsoftware.product;

import com.mizu20040814.accountingsoftware.product.dto.ProductRequest;
import com.mizu20040814.accountingsoftware.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定されたIDの商品が見つかりません: " + id));
    }

    public Product create(ProductRequest request) {
        validateProductRequest(request);
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        return productRepository.save(product);
    }

    public Product update(Long id, ProductUpdateRequest request) {
        validateProductUpdateRequest(request);
        Product product = findByIdOrThrow(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setEnabled(request.isEnabled());
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findByIdOrThrow(id);
        product.setEnabled(false);
        productRepository.save(product);
    }

    // ==================== バリデーション ====================

    private void validateProductRequest(ProductRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("商品名は必須です");
        }
        if (request.getName().length() > 100) {
            throw new IllegalArgumentException("商品名は100文字以内で入力してください");
        }
        if (request.getPrice() < 0) {
            throw new IllegalArgumentException("価格は0以上で入力してください");
        }
        if (request.getStock() < 0) {
            throw new IllegalArgumentException("在庫数は0以上で入力してください");
        }
    }

    private void validateProductUpdateRequest(ProductUpdateRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new IllegalArgumentException("商品名は必須です");
        }
        if (request.getName().length() > 100) {
            throw new IllegalArgumentException("商品名は100文字以内で入力してください");
        }
        if (request.getPrice() < 0) {
            throw new IllegalArgumentException("価格は0以上で入力してください");
        }
        if (request.getStock() < 0) {
            throw new IllegalArgumentException("在庫数は0以上で入力してください");
        }
    }

    // ==================== ヘルパー ====================

    private Product findByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("商品が見つかりません: " + id));
    }
}
