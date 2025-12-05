package productManager.example.productManager.DTO;

import productManager.example.productManager.Entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .sku(product.getSku())
                .quantity(product.getQuantity())
                .build();
    }

    public static Product toEntity(CreateProductRequest request) {
        if (request == null) {
            return null;
        }

        return Product.builder()
                .price(request.getPrice())
                .name(request.getName())
                .sku(request.getSku())
                .quantity(request.getQuantity())
                .build();
    }

    public static void updateProductEntity(Product product, UpdateProductRequest request){
        if(product==null||request==null)
        {
            return;
        }
        if(request.getName()!=null)
        {
            product.setName(request.getName());
        }
        if(request.getPrice()!=null)
        {
            product.setPrice(request.getPrice());
        }
        if(request.getQuantity()!=null)
        {
            product.setQuantity(request.getQuantity());
        }
        if(request.getSku()!=null)
        {
            product.setSku(request.getSku());
        }



    }
}
