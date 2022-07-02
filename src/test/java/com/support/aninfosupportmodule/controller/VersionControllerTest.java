package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.service.VersionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VersionControllerTest extends ControllerTest {

    @MockBean
    private VersionService versionService;

    @Test
    void getProducts() throws Exception {
        Map<Long, Product> products = Map.of(1L, Product.builder().build());

        when(versionService.getProducts()).thenReturn(products);

        mvc.perform(get("/v1/soporte/product"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));

        verify(versionService).getProducts();
    }
}