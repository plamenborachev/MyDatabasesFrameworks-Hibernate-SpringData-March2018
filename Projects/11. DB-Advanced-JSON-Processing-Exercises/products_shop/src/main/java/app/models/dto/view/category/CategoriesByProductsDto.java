package app.models.dto.view.category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesByProductsDto {

    @XmlElement(name = "category")
    private List<CategoryByProductsDto> categoryByProductsDtos;

    public CategoriesByProductsDto() {
    }

    public List<CategoryByProductsDto> getCategoryByProductsDtos() {
        return this.categoryByProductsDtos;
    }

    public void setCategoryByProductsDtos(List<CategoryByProductsDto> categoryByProductsDtos) {
        this.categoryByProductsDtos = categoryByProductsDtos;
    }
}
