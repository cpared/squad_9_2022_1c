package com.support.aninfosupportmodule.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.support.aninfosupportmodule.dto.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Repository
public class VersionRepository {

    private final static String FILEPATH = "products.json";
    private final HashMap<Long, Product> PRODUCTS = new HashMap<>();

    public VersionRepository() throws IOException {
        loadProducts();
    }

    private void loadProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource(FILEPATH).getInputStream();
        List<Product> productsBuff = mapper.readValue(file, new TypeReference<>() {});

        productsBuff.forEach(p -> PRODUCTS.put(p.getId(), p));
    }

    public List<String> getVersionsByProductId(Long productId) {
        return PRODUCTS.get(productId).getVersions();
    }
}
