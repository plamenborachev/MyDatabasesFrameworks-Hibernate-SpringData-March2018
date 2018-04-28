package app.models.dto.view.category;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductsDto implements Serializable {

    @Expose
    @XmlAttribute(name = "name")
    private String category;

    @Expose
    @XmlElement(name = "products-count")
    private int productsCount;

    @Expose
    @XmlElement(name = "average-price")
    private BigDecimal averagePrice;

    @Expose
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryByProductsDto() {
    }

    public CategoryByProductsDto(String category, int productsCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return this.productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return this.averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return this.totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
