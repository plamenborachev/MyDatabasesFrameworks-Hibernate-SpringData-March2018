package app.service.api;

import app.models.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addToDb(Product product);

    long getCountOfProducts();

    List<Product> getAllProductsInPriceRangeWithoutBuyer(BigDecimal minPrice, BigDecimal maxPrice);

}
