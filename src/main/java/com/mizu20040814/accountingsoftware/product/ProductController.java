package com.mizu20040814.accountingsoftware.product;

import com.mizu20040814.accountingsoftware.product.dto.ProductRequest;
import com.mizu20040814.accountingsoftware.product.dto.ProductResponse;
import com.mizu20040814.accountingsoftware.product.dto.ProductUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id){
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request){
        return ProductResponse.from(productService.create(request));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductUpdateRequest updateRequest){
        return ProductResponse.from(productService.update(id,updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
