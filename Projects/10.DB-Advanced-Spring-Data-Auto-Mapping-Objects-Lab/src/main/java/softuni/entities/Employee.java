package softuni.entities;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Proxy(lazy = false)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private BigDecimal salary;

    private LocalDate birthday;

    @ManyToOne
    private Address address;

    private boolean onHoliday;

    @ManyToOne
    private Employee manager;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private List<Employee> servants;

    public Employee() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getSalary() {
        return this.salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isOnHoliday() {
        return this.onHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        this.onHoliday = onHoliday;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getServants() {
        return this.servants;
    }

    public void setServants(List<Employee> servants) {
        this.servants = servants;
    }
}
