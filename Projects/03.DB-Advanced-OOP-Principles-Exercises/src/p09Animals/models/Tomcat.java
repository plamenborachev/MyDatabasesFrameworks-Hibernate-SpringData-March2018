package p09Animals.models;

public class Tomcat extends Cat {
    public Tomcat(String name, String age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected void setGender(String gender) {
//        if (!gender.equals("Male")){
//            throw new IllegalArgumentException("Invalid input!");
//        }
        super.setGender("Male");
    }

    @Override
    public String produceSound() {
        return "Give me one million b***h";
    }
}
