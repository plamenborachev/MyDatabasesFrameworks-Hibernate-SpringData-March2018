package p12FindEmployeesByFirstName;

import entities.Address;
import entities.Employee;
import entities.Town;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FindEmployeesByFirstName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String namePattern = reader.readLine();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        List<Employee> employees = em
                .createQuery("FROM Employee e WHERE e.firstName LIKE CONCAT(?, '%') ",
                    Employee.class)
                .setParameter(0, namePattern)
                .getResultList();

        StringBuilder sb = new StringBuilder();

        employees.forEach(e -> sb.append(String.format("%s %s - %s - ($%.2f)",
                e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()))
                .append(System.lineSeparator()));

        System.out.println(sb.toString().trim());

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
