package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.Product;
import com.support.aninfosupportmodule.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testGetVersions() {
        List<String> versions = List.of("1.0.0", "1.0.1");
        Product product = Product.builder()
                .id(1L)
                .name("Stock System 2020")
                .versions(versions)
                .build();

        when(productRepository.getProductById(1L)).thenReturn(Optional.of(product));

        List<String> result = productService.getVersions(1L);

        assertThat(result).isEqualTo(versions);

        verify(productRepository).getProductById(1L);
    }

    @Test
    void testGetProducts() {
        List<String> versions = List.of("1.0.0", "1.0.1");
        Product product = Product.builder()
                .id(1L)
                .name("Stock System 2020")
                .versions(versions)
                .build();
        Map<Long, Product> products = Map.of(1L, product);

        when(productRepository.getProducts()).thenReturn(products);

        Map<Long, Product> result = productService.getProducts();

        assertThat(result).isEqualTo(products);

        verify(productRepository).getProducts();
    }

    @Test
    void testGetProduct() {
        List<String> versions = List.of("1.0.0", "1.0.1");
        Product product = Product.builder()
                .id(1L)
                .name("Stock System 2020")
                .versions(versions)
                .build();

        when(productRepository.getProductById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);

        assertThat(result).isEqualTo(product);

        verify(productRepository).getProductById(1L);
    }

    @Test
    void testGetProduct_NotFound() {
        when(productRepository.getProductById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProduct(1L))
                .isExactlyInstanceOf(NotFoundException.class)
                .hasMessage("Product does not exist: 1");

        verify(productRepository).getProductById(1L);
    }
}