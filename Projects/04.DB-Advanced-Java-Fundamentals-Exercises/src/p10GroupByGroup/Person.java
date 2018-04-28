package p10GroupByGroup;

public class Person {
    private String name;
    private int group;

    public Person(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public int getGroup() {
        return this.group;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
