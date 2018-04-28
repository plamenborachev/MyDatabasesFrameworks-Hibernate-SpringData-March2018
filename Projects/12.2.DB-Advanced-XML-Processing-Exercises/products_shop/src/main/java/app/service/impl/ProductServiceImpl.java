package app.service.impl;

import app.models.entity.Product;
import app.repository.ProductRepository;
import app.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addToDb(Product product) {
        this.productRepository.saveAndFlush(product);
    }

    @Override
    public long getCountOfProducts() {
        return this.productRepository.count();
    }

    @Override
    public List<Product> getAllProductsInPriceRangeWithoutBuyer(BigDecimal minPrice, BigDecimal
            maxPrice) {
        return this.productRepository.getAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(minPrice, maxPrice);
    }
}
