package p08Vehicles;

import java.text.DecimalFormat;

public abstract class BaseVehicle implements Vehicle{

    private double fuelQuantity;
    private double fuelConsumption;

    protected BaseVehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    @Override
    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    @Override
    public String drive(double distance) {
        String result = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.################");
        if (this.getFuelQuantity() / this.getFuelConsumption() >= distance){
            this.fuelQuantity -= distance * this.getFuelConsumption();
            result = String.format("%s travelled %s km",
                    this.getClass().getSimpleName(), decimalFormat.format(distance));
        } else {
            result = this.getClass().getSimpleName() + " needs refueling";
        }
        return result;
    }

    @Override
    public void refuel(double liters) {
        this.fuelQuantity += liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}
