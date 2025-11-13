package com.example.Product.Manager.Service;

import com.example.Product.Manager.Entity.Product;
import com.example.Product.Manager.Exception.InvalidSkuFormatException;
import com.example.Product.Manager.Exception.ProductNotFoundException;
import com.example.Product.Manager.Exception.SkuAlreadyExistsException;
import com.example.Product.Manager.Repository.ProductRepository;
import com.example.Product.Manager.Response.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    private static final Pattern SKU_PATTERN = Pattern.compile("^SKU-[A-Za-z0-9]{8}$");

    private void validateSkuFormat(String sku) {
        if (!SKU_PATTERN.matcher(sku).matches()) {
            throw new InvalidSkuFormatException("SKU must start with 'SKU-' followed by 8 alphanumeric characters");
        }
    }

    public ApiResponse<Product> createProduct(Product product) {
        log.debug("Received request to create product: {}", product);

        String sku = product.getSku();
        if (!StringUtils.hasText(sku)) {
            throw new InvalidSkuFormatException("SKU must not be blank and must match required format");
        }

        validateSkuFormat(sku);

        if (productRepository.existsBySku(sku)) {
            throw new SkuAlreadyExistsException("SKU already exists: " + sku);
        }

        Product saved = productRepository.save(product);
        log.info("Product created with ID: {} and SKU: {}", saved.getId(), saved.getSku());

        return new ApiResponse<>(
                "success",
                HttpStatus.CREATED.value(),
                "Product created successfully",
                false,
                saved
        );
    }

    public ApiResponse<List<Product>> getAllProducts() {
        log.debug("Received request to get all products");
        List<Product> list = productRepository.findAll();
        log.info("Fetched {} products", list.size());

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched all products successfully",
                false,
                list
        );
    }

    public ApiResponse<Product> getProductById(Long id) {
        log.debug("Received request to get product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched product successfully",
                false,
                product
        );
    }

    public ApiResponse<Product> updateProduct(Long id, Product product) {
        log.debug("Received request to update product id: {} payload: {}", id, product);

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (product.getSku() != null && !product.getSku().equals(existing.getSku())) {
            throw new InvalidSkuFormatException("SKU of an existing product cannot be changed");
        }

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setQuantity(product.getQuantity());
        existing.setStatus(product.getStatus());

        Product updated = productRepository.save(existing);
        log.info("Product updated with ID: {} and SKU: {}", updated.getId(), updated.getSku());

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Product updated successfully",
                false,
                updated
        );
    }

    public ApiResponse<Void> deleteProduct(Long id) {
        log.debug("Received request to delete product id: {}", id);
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        productRepository.delete(existing);
        log.info("Product deleted with ID: {} and SKU: {}", existing.getId(), existing.getSku());

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Product deleted successfully",
                false,
                null
        );
    }
}
