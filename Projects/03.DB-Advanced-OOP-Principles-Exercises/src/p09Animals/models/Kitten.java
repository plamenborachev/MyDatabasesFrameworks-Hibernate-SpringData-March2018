package p09Animals.models;

public class Kitten extends Cat {

    public Kitten(String name, String age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected void setGender(String gender) {
//        if (!gender.equals("Female")){
//            throw new IllegalArgumentException("Invalid input!");
//        }
        super.setGender("Female");
    }

    @Override
    public String produceSound() {
        return "Miau";
    }
}
