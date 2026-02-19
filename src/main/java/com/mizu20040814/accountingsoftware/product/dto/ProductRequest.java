package com.mizu20040814.accountingsoftware.product.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private int price;
    private int stock;
}