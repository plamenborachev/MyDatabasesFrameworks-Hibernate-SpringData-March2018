package p05EmployeesFromDepartment;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employees = em.createQuery("FROM Employee WHERE department.name = 'Research and Development' ORDER BY salary, id", Employee.class)
                .getResultList();

        employees.forEach(e -> System.out.println(String.format("%s %s from %s - $%.2f", e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary())));

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
