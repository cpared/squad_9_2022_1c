package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/product")
@CrossOrigin
public class VersionController {

    private final VersionService versionService;

    @GetMapping()
    public Map<Long, Product> getProducts() {
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
