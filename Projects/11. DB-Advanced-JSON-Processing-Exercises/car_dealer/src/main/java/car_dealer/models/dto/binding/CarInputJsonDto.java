package car_dealer.models.dto.binding;

import com.google.gson.annotations.Expose;

import java.math.BigInteger;

public class CarInputJsonDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private long travelledDistance;

    public CarInputJsonDto() {
    }

    public String getMake() {
        return this.make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return this.travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
