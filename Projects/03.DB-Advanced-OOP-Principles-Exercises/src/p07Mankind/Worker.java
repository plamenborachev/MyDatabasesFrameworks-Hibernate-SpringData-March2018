package p07Mankind;

public class Worker extends BaseHuman {

    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    public double getWeekSalary() {
        return this.weekSalary;
    }

    public double getWorkingHours() {
        return this.workingHours;
    }

    public double salaryPerHour(){
        return (this.weekSalary / (this.workingHours * 7)) ;
    }

    private void setWeekSalary(double weekSalary) {
        if (weekSalary <= 10){
            throw  new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public void setWorkingHours(double workingHours) {
        if (workingHours < 1 || workingHours > 12){
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workingHours = workingHours;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(String.format("Week Salary: %.2f", this.weekSalary)).append(System.lineSeparator());
        sb.append(String.format("Hours per day: %.2f", this.workingHours)).append(System.lineSeparator());
        sb.append(String.format("Salary per hour: %.2f", this.salaryPerHour())).append(System.lineSeparator());
        return sb.toString();
    }
}
