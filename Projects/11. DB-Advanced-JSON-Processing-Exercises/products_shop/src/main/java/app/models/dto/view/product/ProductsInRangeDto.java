package app.models.dto.view.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeDto {

    @XmlElement(name = "product")
    private List<ProductInRangeDto> productInRangeDtos;

    public ProductsInRangeDto() {
    }

    public List<ProductInRangeDto> getProductInRangeDtos() {
        return this.productInRangeDtos;
    }

    public void setProductInRangeDtos(List<ProductInRangeDto> productInRangeDtos) {
        this.productInRangeDtos = productInRangeDtos;
    }
}
