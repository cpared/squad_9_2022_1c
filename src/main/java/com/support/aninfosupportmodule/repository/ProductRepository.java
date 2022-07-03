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
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final static String FILEPATH = "products.json";
    private final Map<Long, Product> PRODUCTS = new HashMap<>();

    public ProductRepository() throws IOException {
        loadProducts();
    }

    private void loadProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource(FILEPATH).getInputStream();
        List<Product> productsBuff = mapper.readValue(file, new TypeReference<>() {});

        productsBuff.forEach(p -> PRODUCTS.put(p.getId(), p));
    }

    public Map<Long, Product> getProducts() {
        return PRODUCTS;
    }

    public Optional<Product> getProductById(Long productId) {
        return Optional.ofNullable(getProducts().get(productId));
    }
}
