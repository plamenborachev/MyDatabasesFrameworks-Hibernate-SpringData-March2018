package app.models.dto.view.user.query4;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSoldProductsDto implements Serializable{

    @Expose
    @XmlAttribute(name = "count")
    private int usersCount;

    @Expose
    @SerializedName(value = "users")
    @XmlElement(name = "user")
    private List<UserWithSoldProductsDto> userWithSoldProductsDto;

    public UsersWithSoldProductsDto() {
        this.userWithSoldProductsDto = new LinkedList<>();
    }

    public UsersWithSoldProductsDto(List<UserWithSoldProductsDto> userWithSoldProductsDtos) {
        this.userWithSoldProductsDto = userWithSoldProductsDtos;
        this.setUsersCount(this.userWithSoldProductsDto.size());
    }

    public int getUsersCount() {
        return this.usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithSoldProductsDto> getUserWithSoldProductsDto() {
        return this.userWithSoldProductsDto;
    }

    public void setUserWithSoldProductsDto(List<UserWithSoldProductsDto> userWithSoldProductsDto) {
        this.userWithSoldProductsDto = userWithSoldProductsDto;
    }
}
