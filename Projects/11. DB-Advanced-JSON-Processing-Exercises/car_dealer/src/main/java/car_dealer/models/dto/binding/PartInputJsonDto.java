package car_dealer.models.dto.binding;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartInputJsonDto {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private int quantity;

    public PartInputJsonDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
