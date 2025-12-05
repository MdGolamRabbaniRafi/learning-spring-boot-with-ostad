package productManager.example.productManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productManager.example.productManager.Entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);
}
