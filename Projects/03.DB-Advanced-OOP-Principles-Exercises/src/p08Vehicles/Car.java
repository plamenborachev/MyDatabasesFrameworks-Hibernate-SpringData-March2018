package p08Vehicles;

public class Car extends BaseVehicle {

    protected Car(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    public double getFuelConsumption() {
        return super.getFuelConsumption() + 0.9;
    }
}
