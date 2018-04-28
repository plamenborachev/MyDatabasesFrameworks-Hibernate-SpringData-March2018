package p03ContainsEmployee;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ContainsEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("\\s+");
        String firstName = input[0];
        String lastName = input[1];

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        List<Employee> employee =
                em.createQuery("FROM Employee WHERE firstName = ? AND lastName = ?", Employee.class)
                        .setParameter(0, firstName)
                        .setParameter(1, lastName)
                        .getResultList();
        if (employee.size() == 1){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
