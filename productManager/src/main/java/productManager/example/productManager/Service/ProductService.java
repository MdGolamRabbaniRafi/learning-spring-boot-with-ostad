package productManager.example.productManager.Service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import productManager.example.productManager.DTO.CreateProductRequest;
import productManager.example.productManager.DTO.ProductDTO;
import productManager.example.productManager.DTO.ProductMapper;
import productManager.example.productManager.DTO.UpdateProductRequest;
import productManager.example.productManager.Entity.Product;
import productManager.example.productManager.Exception.InvalidSkuFormatException;
import productManager.example.productManager.Exception.ProductNotFoundException;
import productManager.example.productManager.Exception.SkuAlreadyExistsException;
import productManager.example.productManager.Repository.ProductRepository;
import productManager.example.productManager.Response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    private static final Pattern SKU_PATTERN = Pattern.compile("^SKU-[A-Za-z0-9]{8}$");

    private void validateSkuFormat(String sku) {
        if (!SKU_PATTERN.matcher(sku).matches()) {
            throw new InvalidSkuFormatException("SKU must start with 'SKU-' followed by 8 alphanumeric characters");
        }
    }

    public ApiResponse<Product> createProduct(CreateProductRequest request) {
        log.debug("Received request to create product: {}", request);

        String sku = request.getSku();
        if (!StringUtils.hasText(sku)) {
            throw new InvalidSkuFormatException("SKU must not be blank and must match required format");
        }

        validateSkuFormat(sku);

        if (productRepository.existsBySku(sku)) {
            throw new SkuAlreadyExistsException("SKU already exists: " + sku);
        }

        Product product = ProductMapper.toEntity(request);

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

    @Transactional(readOnly = true)
    public ApiResponse<Page<ProductDTO>> getAllProducts(Pageable pageable) {
        log.debug("Received request to get all products");

        Page<Product> page = productRepository.findAll(pageable);

        Page<ProductDTO> dtoPage = page.map(ProductMapper::toDTO);

        log.info("Fetched {} products", page.getTotalElements());

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched all products successfully",
                false,
                dtoPage
        );
    }


    public ApiResponse<ProductDTO> getProductById(Long id) {
        log.debug("Received request to get product by id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        ProductDTO productDTO = ProductMapper.toDTO(product);

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched product successfully",
                false,
                productDTO
        );
    }

    public ApiResponse<ProductDTO> updateProduct(Long id, UpdateProductRequest request) {
        log.debug("Received request to update product id: {} payload: {}", id, request);

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        if (request.getSku() != null && !request.getSku().equals(existing.getSku())) {
            throw new InvalidSkuFormatException("SKU of an existing product cannot be changed");
        }

        existing.setName(request.getName());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());

        Product updated = productRepository.save(existing);

        ProductDTO productDTO = ProductMapper.toDTO(updated);


        log.info("Product updated with ID: {} and SKU: {}", updated.getId(), updated.getSku());

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Product updated successfully",
                false,
                productDTO
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

    public Product findProductBySku(String sku) {
        return productRepository.findBySku(sku)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with SKU " + sku + " not found")
                );
    }

    public Product restockProduct(String sku, int quantityToAdd) {
        Product product = productRepository.findBySku(sku)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product with SKU " + sku + " not found")
                );

        product.setQuantity(product.getQuantity() + quantityToAdd);

        return productRepository.save(product);
    }

}
