package productManager.example.productManager.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    @Size(max = 255, message = "SKU must not exceed 255 characters")
    private String sku;

    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Min(value = 0, message = "Price must be positive")
    private Double price;
}
