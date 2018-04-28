package app.models.dto.view.product.query4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldByUserDto implements Serializable{

    @Expose
    @XmlAttribute(name = "count")
    private int count;

    @Expose
    @SerializedName(value = "products")
    @XmlElement(name = "product")
    private List<ProductSoldByUserDto> productSoldByUserDtos;

    public ProductsSoldByUserDto() {
        this.productSoldByUserDtos = new LinkedList<>();

    }

    public ProductsSoldByUserDto(List<ProductSoldByUserDto> productSoldByUserDtos) {
        this.productSoldByUserDtos = productSoldByUserDtos;
        this.setCount(this.productSoldByUserDtos.size());
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductSoldByUserDto> getProductSoldByUserDtos() {
        return this.productSoldByUserDtos;
    }

    public void setProductSoldByUserDtos(List<ProductSoldByUserDto> productSoldByUserDtos) {
        this.productSoldByUserDtos = productSoldByUserDtos;
    }
}
