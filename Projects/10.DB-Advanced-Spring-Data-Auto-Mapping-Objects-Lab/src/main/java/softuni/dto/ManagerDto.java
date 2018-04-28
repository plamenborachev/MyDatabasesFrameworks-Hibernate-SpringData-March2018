package softuni.dto;

import java.util.List;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private List<EmployeeDto> servants;

    public ManagerDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getServants() {
        return this.servants;
    }

    public void setServants(List<EmployeeDto> servants) {
        this.servants = servants;
    }
}
