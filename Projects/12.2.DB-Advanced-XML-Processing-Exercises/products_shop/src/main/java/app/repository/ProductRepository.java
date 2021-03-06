package app.repository;

import app.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal price, BigDecimal price2);

}
