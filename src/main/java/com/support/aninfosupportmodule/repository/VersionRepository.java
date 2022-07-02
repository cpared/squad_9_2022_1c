package com.support.aninfosupportmodule.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.exception.InternalServerException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class VersionRepository {

    private final static String FILEPATH = "classpath:products.json";

    private List<Product> productsBuff = new ArrayList<>();

    private HashMap<Long, Product> products = new HashMap<>();

    public VersionRepository() throws InternalServerException {
        loadProducts();
    }

    private void loadProducts() throws InternalServerException {
        try {
            ObjectMapper mapper = new ObjectMapper();

            if(ResourceUtils.getFile(FILEPATH).length() == 0) return;
            productsBuff = mapper.readValue(ResourceUtils.getFile(FILEPATH), new TypeReference<>() {});

            for(int i = 0; i < productsBuff.size(); i++){
                products.put(productsBuff.get(i).getId(), productsBuff.get(i));
            }

        } catch (Exception ex){
            ex.printStackTrace();
            throw new InternalServerException("File Error");
        }
    }

    public List<String> getVersionsByProductId(Long productId) {
        return products.get(productId).getVersions();
    }
}
