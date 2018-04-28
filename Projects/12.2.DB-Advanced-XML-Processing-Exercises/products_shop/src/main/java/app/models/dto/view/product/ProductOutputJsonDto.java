package app.models.dto.view.product;

import app.models.dto.view.product.query4.ProductSoldByUserDto;

import java.util.HashSet;
import java.util.Set;

public class ProductOutputJsonDto {

    private int count;

    private Set<ProductSoldByUserDto> productSoldByUserDtoSet;

    public ProductOutputJsonDto() {
        this.productSoldByUserDtoSet = new HashSet<>();
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Set<ProductSoldByUserDto> getProductSoldByUserDtoSet() {
        return this.productSoldByUserDtoSet;
    }

    public void setProductSoldByUserDtoSet(Set<ProductSoldByUserDto> productSoldByUserDtoSet) {
        this.productSoldByUserDtoSet = productSoldByUserDtoSet;
    }
}
