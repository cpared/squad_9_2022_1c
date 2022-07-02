package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/soporte/product")
public class VersionController {

    private final VersionService versionService;

    @GetMapping()
    public HashMap<Long, Product> getProducts() {
        return versionService.getProducts();
    }

    @GetMapping("{productId}")
    public Product getProduct(@PathVariable Long productId) {
        return versionService.getProduct(productId);
    }

    @GetMapping("/version/{productId}")
    public List<String> getVersions(@PathVariable Long productId) {
        return versionService.getVersions(productId);
    }
}
