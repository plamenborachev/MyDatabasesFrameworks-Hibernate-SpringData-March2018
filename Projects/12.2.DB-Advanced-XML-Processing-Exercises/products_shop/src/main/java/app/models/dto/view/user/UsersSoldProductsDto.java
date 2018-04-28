package app.models.dto.view.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsDto {

    @XmlElement(name = "user")
    private List<UserSoldProductsDto> userSoldProductsDtos;

    public UsersSoldProductsDto() {
    }

    public List<UserSoldProductsDto> getUserSoldProductsDtos() {
        return this.userSoldProductsDtos;
    }

    public void setUserSoldProductsDtos(List<UserSoldProductsDto> userSoldProductsDtos) {
        this.userSoldProductsDtos = userSoldProductsDtos;
    }
}
