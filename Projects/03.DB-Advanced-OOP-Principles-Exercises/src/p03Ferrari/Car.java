package p03Ferrari;

public interface Car {

    String getModel();

    String getDriverName();

    default String useBrakes(){
        return "Brakes!";
    };

    default String pushTheGasPedal(){
        return "Zadu6avam sA!";
    };
}
