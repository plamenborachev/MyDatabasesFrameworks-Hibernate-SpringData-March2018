package p09Animals.models;

public class Frog extends BaseAnimal {
    public Frog(String name, String age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Frogggg";
    }
}
