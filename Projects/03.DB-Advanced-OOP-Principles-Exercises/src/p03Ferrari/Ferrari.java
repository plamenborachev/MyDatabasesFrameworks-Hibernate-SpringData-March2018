package p03Ferrari;

public class Ferrari implements Car {

    private String model;
    private String driverName;

    public Ferrari(String driverName) {
        this.model = "488-Spider";
        this.driverName = driverName;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getDriverName() {
        return this.driverName;
    }
}
