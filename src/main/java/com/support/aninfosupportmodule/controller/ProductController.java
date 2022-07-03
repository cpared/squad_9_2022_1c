package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/product")
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public Map<Long, Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/version/{productId}")
    public List<String> getVersions(@PathVariable Long productId) {
        return productService.getVersions(productId);
    }
}
