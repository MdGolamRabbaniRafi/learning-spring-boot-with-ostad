package productManager.example.productManager.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import productManager.example.productManager.Entity.Product;
import productManager.example.productManager.Exception.ProductNotFoundException;
import productManager.example.productManager.Repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .sku("SKU123")
                .name("Test Product")
                .quantity(10)
                .build();
    }

    @Test
    void testFindProductBySku_Success() {

        when(productRepository.findBySku("SKU123")).thenReturn(Optional.of(product));

        Product found = productService.findProductBySku("SKU123");

        assertNotNull(found);
        assertEquals("SKU123", found.getSku());
        assertEquals("Test Product", found.getName());
        verify(productRepository, times(1)).findBySku("SKU123");
    }

    @Test
    void testFindProductBySku_NotFound() {
        when(productRepository.findBySku("SKU999")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () ->
                productService.findProductBySku("SKU999"));

        verify(productRepository, times(1)).findBySku("SKU999");
    }

    @Test
    void testRestockProduct_Success() {
        Product mockProduct = new Product();
        mockProduct.setSku("SKU123");
        mockProduct.setQuantity(5);

        when(productRepository.findBySku("SKU123")).thenReturn(Optional.of(mockProduct));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Product updated = productService.restockProduct("SKU123", 10);

        assertEquals(15, updated.getQuantity()); // original 5 + added 10
        verify(productRepository, times(1)).findBySku("SKU123");
        verify(productRepository, times(1)).save(mockProduct);
    }

    @Test
    void testRestockProduct_NotFound() {
        when(productRepository.findBySku("SKU999")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () ->
                productService.restockProduct("SKU999", 10));

        verify(productRepository, times(1)).findBySku("SKU999");
        verify(productRepository, never()).save(any(Product.class));
    }


}