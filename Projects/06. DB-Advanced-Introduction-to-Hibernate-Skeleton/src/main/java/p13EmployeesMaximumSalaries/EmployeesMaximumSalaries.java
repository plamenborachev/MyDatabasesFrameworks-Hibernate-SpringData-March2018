package p13EmployeesMaximumSalaries;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees = em
                .createQuery("SELECT e FROM Employee e GROUP BY e.department.id HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Employee.class)
                .getResultList();

        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {
            Optional<BigDecimal> maxSalary = employee.getDepartment().getEmployees().stream()
                    .map(Employee::getSalary)
                    .max(BigDecimal::compareTo);

            maxSalary.ifPresent(bigDecimal -> sb.append(String.format("%s - %s",
                    employee.getDepartment().getName(), bigDecimal))
                    .append(System.lineSeparator()));
        }

        System.out.println(sb.toString().trim());

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
