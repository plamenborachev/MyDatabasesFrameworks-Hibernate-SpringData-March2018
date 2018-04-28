package p06AddingNewAddressAndUpdatingEmployee;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName = reader.readLine();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);

        try{
            Employee employee = (Employee) em.createQuery("FROM Employee WHERE lastName = ?")
                    .setParameter(0, lastName)
                    .getSingleResult();

            employee.setAddress(address);
        } catch (NoResultException nre){
            System.out.println("Employee does not exist!");
        }

        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
