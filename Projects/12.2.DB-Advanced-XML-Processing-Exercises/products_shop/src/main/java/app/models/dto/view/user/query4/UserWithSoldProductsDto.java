package app.models.dto.view.user.query4;

import app.models.dto.view.product.query4.ProductsSoldByUserDto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductsDto implements Serializable{

    @Expose
    @XmlAttribute(name = "first-name")
    private String firstName;

    @Expose
    @XmlAttribute(name = "last-name")
    private String lastName;

    @Expose
    @XmlAttribute
    private int age;

    @Expose
    @SerializedName(value = "soldProducts")
    @XmlElement(name = "sold-products")
    private ProductsSoldByUserDto productsSoldByUserDto;

    public UserWithSoldProductsDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductsSoldByUserDto getProductsSoldByUserDto() {
        return this.productsSoldByUserDto;
    }

    public void setProductsSoldByUserDto(ProductsSoldByUserDto productsSoldByUserDto) {
        this.productsSoldByUserDto = productsSoldByUserDto;
    }
}
