package com.mizu20040814.accountingsoftware.product.dto;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private String name;
    private int price;
    private int stock;
    private boolean enabled;
}
