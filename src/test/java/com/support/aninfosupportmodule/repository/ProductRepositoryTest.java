package com.support.aninfosupportmodule.repository;

import com.support.aninfosupportmodule.dto.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @Test
    void testGetProducts() {
        Map<Long, Product> products = buildProducts();

        Map<Long, Product> result = productRepository.getProducts();

        assertThat(result).isEqualTo(products);
    }

    @Test
    void testGetProductById() {
        Product product = Product.builder()
                .id(3L)
                .name("Sony Speaker Manager")
                .versions(List.of("1.0.0", "1.0.1", "1.0.2")).build();

        Optional<Product> result = productRepository.getProductById(3L);

        assertThat(result).isEqualTo(Optional.of(product));
    }

    @Test
    void testGetProductById_NotFound() {
        Optional<Product> result = productRepository.getProductById(10L);

        assertThat(result).isEqualTo(Optional.empty());
    }

    public static Map<Long, Product> buildProducts() {
        return Map.of(
                1L, Product.builder()
                        .id(1L)
                        .name("Microsoft Excel")
                        .versions(List.of("0.1.0", "0.1.1")).build(),
                2L, Product.builder()
                        .id(2L)
                        .name("Google Lens")
                        .versions(List.of("1.0.0", "2.0.0")).build(),
                3L, Product.builder()
                        .id(3L)
                        .name("Sony Speaker Manager")
                        .versions(List.of("1.0.0", "1.0.1", "1.0.2")).build(),
                4L, Product.builder()
                        .id(4L)
                        .name("Nintendo Sharing System")
                        .versions(List.of("1.0.0", "1.1.0", "1.5.0", "2.0.0")).build(),
                5L, Product.builder()
                        .id(5L)
                        .name("XBox Store Stock System")
                        .versions(List.of("1.0.0", "1.1.0", "1.2.0", "1.2.3")).build(),
                6L, Product.builder()
                        .id(6L)
                        .name("Discord Music Bot")
                        .versions(List.of("0.0.1", "0.0.2")).build(),
                7L, Product.builder()
                        .id(7L)
                        .name("Slack Huddle")
                        .versions(List.of("1.0.0")).build(),
                8L, Product.builder()
                        .id(8L)
                        .name("Bugisoft AC Origins")
                        .versions(List.of("1.0.0", "1.1.0")).build());
    }
}