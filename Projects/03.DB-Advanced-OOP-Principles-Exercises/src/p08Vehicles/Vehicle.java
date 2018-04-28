package p08Vehicles;

public interface Vehicle {

    double getFuelQuantity();

    double getFuelConsumption();

    String drive(double distance);

    void refuel(double liters);
}
