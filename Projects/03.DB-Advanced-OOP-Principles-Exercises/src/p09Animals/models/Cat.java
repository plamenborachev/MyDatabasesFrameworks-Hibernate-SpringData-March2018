package p09Animals.models;

public class Cat extends BaseAnimal {
    public Cat(String name, String age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "MiauMiau";
    }
}
