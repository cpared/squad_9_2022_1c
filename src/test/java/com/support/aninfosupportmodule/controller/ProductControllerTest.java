package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends ControllerTest {

    @MockBean
    private ProductService productService;

    @Test
    void getProducts() throws Exception {
        Map<Long, Product> products = Map.of(1L, Product.builder().build());

        when(productService.getProducts()).thenReturn(products);

        mvc.perform(get("/v1/support/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));

        verify(productService).getProducts();
    }

    @Test
    void getProduct() throws Exception {
        Product product = Product.builder().build();

        when(productService.getProduct(1L)).thenReturn(product);

        mvc.perform(get("/v1/support/product/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(product)));

        verify(productService).getProduct(1L);
    }

    @Test
    void getVersions() throws Exception {
        List<String> versions = List.of("1.0.0", "1.0.1");

        when(productService.getVersions(1L)).thenReturn(versions);

        mvc.perform(get("/v1/support/product/version/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(versions)));

        verify(productService).getVersions(1L);
    }
}