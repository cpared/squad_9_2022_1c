package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.repository.VersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;

    public List<String> getVersions(Long productId) {
        return versionRepository.getVersionsByProductId(productId);
    }

    public HashMap<Long, Product> getProducts() {
        return versionRepository.getProducts();
    }

    public Product getProduct(Long productId) {
        return versionRepository.getProductById(productId);
    }
}
