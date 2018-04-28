package p08Vehicles;

public class Truck extends BaseVehicle {

    protected Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
    }

    @Override
    public double getFuelConsumption() {
        return super.getFuelConsumption() + 1.6;
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters * 0.95);
    }
}
