package com.mizu20040814.accountingsoftware.product.dto;

import com.mizu20040814.accountingsoftware.product.Product;
import lombok.Data;

@Data
public class ProductResponse {

    private final Long id;
    private final String name;
    private final int price;
    private final int stock;
    private final boolean enabled;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.isEnabled()
        );
    }
}