package p10IncreaseSalaries;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees = em.createQuery("FROM Employee e WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        BigDecimal salaryIncrease = new BigDecimal("1.12");

        StringBuilder sb = new StringBuilder();

        employees
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(salaryIncrease));
                    sb.append(String.format("%s %s ($%.2f)",
                            e.getFirstName(), e.getLastName(), e.getSalary()))
                            .append(System.lineSeparator());
                });

        System.out.println(sb.toString().trim());

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
