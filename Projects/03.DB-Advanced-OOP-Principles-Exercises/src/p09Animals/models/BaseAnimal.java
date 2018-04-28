package p09Animals.models;

import p09Animals.interfaces.Animal;
import p09Animals.interfaces.SoundProducible;

public abstract class BaseAnimal implements Animal, SoundProducible {

    private String name;
    private int age;
    private String gender;

    protected BaseAnimal(String name, String age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        if (name == null || name.length() == 0){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    private void setAge(String age) {
        int currentAge = this.checkAge(age);
        if (currentAge <= 0){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = currentAge;
    }

    protected void setGender(String gender) {
        if (gender == null || gender.length() == 0 || (!"Female".equals(gender) && !"Male".equals(gender))){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }


    public String getGender() {
        return this.gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());
        sb.append(String.format("%s %d %s", this.name, this.age, this.gender)).append(System.lineSeparator());
        sb.append(this.produceSound());
        return sb.toString();
    }

    @Override
    public String produceSound() {
        return "Not implemented!";
    }

    private int checkAge(String age){
        try{
            return Integer.parseInt(age);
        } catch (NumberFormatException nfe){
            throw new IllegalArgumentException("Invalid input!");
        }
    }
}
