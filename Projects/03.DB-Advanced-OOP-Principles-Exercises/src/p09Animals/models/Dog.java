package p09Animals.models;

public class Dog extends BaseAnimal {
    public Dog(String name, String age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "BauBau";
    }
}
