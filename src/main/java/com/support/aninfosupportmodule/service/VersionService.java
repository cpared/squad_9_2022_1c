package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final ProductRepository productRepository;

    public List<String> getVersions(Long productId) {
        return getProduct(productId).getVersions();
    }

    public Map<Long, Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProduct(Long productId) {
        return productRepository.getProductById(productId)
                .orElseThrow(() -> new NotFoundException("Product does not exist: " + productId));
    }
}
