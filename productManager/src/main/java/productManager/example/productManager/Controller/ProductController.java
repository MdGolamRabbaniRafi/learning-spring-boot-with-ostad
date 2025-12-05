package productManager.example.productManager.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import productManager.example.productManager.DTO.CreateProductRequest;
import productManager.example.productManager.DTO.ProductDTO;
import productManager.example.productManager.DTO.UpdateProductRequest;
import productManager.example.productManager.Entity.Product;
import productManager.example.productManager.Response.ApiResponse;
import productManager.example.productManager.Service.ProductService;
import org.springframework.data.domain.Page;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody CreateProductRequest request)
    {
        return ResponseEntity.status(201).body(productService.createProduct(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ProductDTO>>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir)
    {
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("{/id}")
    public ResponseEntity<ApiResponse<ProductDTO>> findOne(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("{/id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@RequestBody UpdateProductRequest request,@PathVariable Long id)
    {
        return ResponseEntity.ok(productService.updateProduct(id,request));
    }


}
